param([switch]$Test, [switch]$Package)
$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    $javaBin = if ($env:JAVA_HOME) { Join-Path $env:JAVA_HOME 'bin' } else { '' }
    $javac = if ($javaBin) { Join-Path $javaBin 'javac.exe' } else { 'javac' }
    $java = if ($javaBin) { Join-Path $javaBin 'java.exe' } else { 'java' }
    $jar = if ($javaBin) { Join-Path $javaBin 'jar.exe' } else { 'jar' }
    New-Item -ItemType Directory -Force build/classes | Out-Null
    $sources = @(Get-ChildItem src -Recurse -Filter *.java | ForEach-Object FullName)
    & $javac --release 8 -encoding UTF-8 -d build/classes @sources
    if ($LASTEXITCODE) { throw 'Falha na compilação.' }
    if ($Test) {
        & $javac --release 8 -encoding UTF-8 -cp build/classes -d build/tests tests/com/NunesDevs/BankTests.java
        if ($LASTEXITCODE) { throw 'Falha ao compilar testes.' }
        & $java -cp 'build/classes;build/tests' com.NunesDevs.BankTests
        if ($LASTEXITCODE) { throw 'Testes falharam.' }
    }
    if ($Package) {
        & $jar cfe build/Bank-Project.jar com.NunesDevs.Ini -C build/classes . -C . LICENSE -C . README.md
        if ($LASTEXITCODE) { throw 'Falha ao empacotar.' }
    }
} finally { Pop-Location }
