package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'RebootAgent'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("RebootAgent")
    name = "Reboot agent"

    vcs {
        root(RelativeId("HttpsGithubComG0t4teamcityCourseCards"))
    }

    steps {
        powerShell {
            scriptMode = script {
                content = """
                    ${'$'}username = "admin"
                    ${'$'}password = "Sosi1989hui2"
                    ${'$'}authInfo = ${'$'}username + ":" + ${'$'}password
                    ${'$'}authInfo = [System.Convert]::ToBase64String([System.Text.Encoding]::Default.GetBytes(${'$'}authInfo))
                    
                    ${'$'}uri = "http://127.0.0.1:8111/httpAuth/app/rest/agents/name:EPBYMINW0119/id"
                    
                    ${'$'}webRequest.Headers["Authorization"] = "Basic " + ${'$'}authInfo
                    ${'$'}webRequest.PreAuthenticate = ${'$'}true 
                    [System.Net.WebResponse] ${'$'}resp = ${'$'}webRequest.GetResponse();
                    ${'$'}rs = ${'$'}resp.GetResponseStream();
                    [System.IO.StreamReader] ${'$'}sr = New-Object System.IO.StreamReader -argumentList ${'$'}rs;
                    [string] ${'$'}id = ${'$'}sr.ReadToEnd();
                    Write-Output "Rebooting Agent ID: ${'$'}id"
                    
                    ${'$'}uri = "http://127.0.0.1:8111/httpAuth/remoteAccess/reboot.html?agent=${'$'}id&rebootAfterBuild=true"
                    
                    ${'$'}webRequest = [System.Net.WebRequest]::Create(${'$'}uri)
                    ${'$'}webRequest.Headers["Authorization"] = "Basic " + ${'$'}authInfo
                    ${'$'}webRequest.PreAuthenticate = ${'$'}true 
                    [System.Net.WebResponse] ${'$'}resp = ${'$'}webRequest.GetResponse();
                    ${'$'}rs = ${'$'}resp.GetResponseStream();
                    [System.IO.StreamReader] ${'$'}sr = New-Object System.IO.StreamReader -argumentList ${'$'}rs;
                    ${'$'}sr.ReadToEnd();
                """.trimIndent()
            }
        }
        script {
            scriptContent = "http://127.0.0.1:{8111}/cli/agentCLI/restart?{EPBYMINW0119}"
        }
    }

    dependencies {
        snapshot(RelativeId("02Chrome")) {
        }
    }
}))

