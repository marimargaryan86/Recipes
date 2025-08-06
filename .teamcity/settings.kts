import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.exec
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2025.03"

project {

    buildType(Build)
    buildType(TestTeamcityPathPrefix)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        step {
            name = "public recipes"
            id = "public_recipes"
            type = "tc:recipe:jetbrains/pin-build@1.0.1"
            executionMode = BuildStep.ExecutionMode.DEFAULT
            param("env.input_comment", "")
            param("plugin.docker.imagePlatform", "")
            param("plugin.docker.imageId", "")
            param("teamcity.step.phase", "")
            param("plugin.docker.run.parameters", "")
            param("env.input_access_token", "")
            param("env.input_build_id", "207")
            param("env.input_server_url", "%teamcity.serverUrl%")
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})

object TestTeamcityPathPrefix : BuildType({
    name = "Test TEAMCITY_PATH_PREFIX"

    steps {
        exec {
            name = "Executable with parameters"
            id = "Executable_with_parameters"
            path = "mytool"
        }
    }
})
