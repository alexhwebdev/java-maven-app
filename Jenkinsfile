// ---------- S9 L8 ----------
#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
        remote: 'https://github.com/alexhwebdev/jenkins-shared-library.git',
        credentialsId: 'docker-hub-repo'
        // docker-hub-repo = github credentials
    ]
)

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment {
        IMAGE_NAME = 'alexhwebdev/nana-demo-app:java-maven-1.0'
    }
    stages {
        stage('build app') {
            steps {
                echo 'building application jar...'
                buildJar()
            }
        }
        stage('build image') {
            steps {
                script {
                    echo 'building the docker image...'
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
        } 
        stage("deploy") {
            steps {
                script {
                    echo 'deploying docker image to EC2...'
                    def dockerCmd = "docker run -p 8080:8080 -d ${IMAGE_NAME}"
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@18.225.181.211 ${dockerCmd}"
                    }
                }
            }               
        }
    }
}
// ---------- Started with this snippet ----------
// pipeline {
//     agent any
//     tools {
//         maven 'maven-3.9'
//     }
//     stages {
//         stage('test') {
//             steps {
//                 echo 'testing the application...'
//             }
//         }
//         stage('build') {
//             steps {
//                 echo 'building the application...'
//             }
//         }
//         stage('deploy') {
//             steps {
//                 script {
//                     def dockerCmd = 'docker run -p 3080:3080 -d alexhwebdev/nana-demo-app:v1'
//                     sshagent(['ec2-server-key']) {
//                         sh "ssh -o StrictHostKeyChecking=no ec2-user@18.225.181.211 ${dockerCmd}"
//                     }
//                 }
//             }
//         }
//     }
// }




// // ---------- java-maven-pipeline, S8 L14 ----------
// #!/user/bin/env groovy

// library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
//     [$class: 'GitSCMSource',
//         remote: 'https://github.com/alexhwebdev/jenkins-shared-library.git',
//         credentialsId: 'docker-hub-repo'
//         // docker-hub-repo = github credentials
//     ]
// )

// // @Library('jenkins-shared-library')
// def gv

// pipeline {
//     agent any
//     tools {
//         maven 'maven-3.9'
//     }
//     stages {
//         stage('init') {
//             steps {
//                 script {
//                     gv = load 'script.groovy'
//                 }
//             }
//         }
//         stage('build jar') {
//             steps {
//                 script {
//                     // gv.buildJar()
//                     buildJar()
//                 }
//             }
//         }
//         stage('build image') {
//             steps {
//                 script {
//                     // gv.buildImage()
//                     buildImage('alexhwebdev/nana-demo-app:jma-3.0')
//                 }
//             }
//         }
//         stage('deploy') {
//             steps {
//                 script {
//                     gv.deployApp()
//                 }
//             }
//         }
//     }
// }


// // ---------- java-maven-pipeline, S8 L11 ----------
// pipeline {
//     agent any

//     stages {
//         stage('test') {
//             steps {
//                 script {
//                     echo "Testing the application..."
//                     echo "Executing pipeline for branch $BRANCH_NAME"
//                 }
//             }
//         }
//         stage('build') {
//             when {
//                 expression {
//                     BRANCH_NAME == 'main'
//                 }
//             }
//             steps {
//                 script {
//                     echo "Building the application..."
//                 }
//             }
//         }
//         stage('deploy') {
//             when {
//                 expression {
//                     BRANCH_NAME == 'main'
//                 }
//             }
//             steps {
//                 script {
//                     echo "Deploying the application..."
//                 }
//             }
//         }
//     }
// }




// // ---------- java-maven-pipeline, S8 L10 ----------
// def gv

// pipeline {
//     agent any
//     tools {
//         maven 'maven-3.9'
//     }
//     stages {
//         stage('init') {
//             steps {
//                 script {
//                     gv = load 'script.groovy'
//                 }
//             }
//         }
//         stage('build jar') {
//             steps {
//                 script {
//                     gv.buildJar()
//                 }
//             }
//         }
//         stage('build image') {
//             steps {
//                 script {
//                     gv.buildImage()
//                 }
//             }
//         }
//         stage('deploy') {
//             steps {
//                 script {
//                     gv.deployApp()
//                 }
//             }
//         }
//     }
// }







// ---------- java-maven-pipeline ----------
// def gv

// pipeline {
//     agent any
//     // tools {
//     //     maven 'maven-3.9'
//     // }
//     // environment {
//     //     NEW_VERSION = '1.3.0'
//     //     // Option 1:
//     //     // SERVER_CREDENTIALS = credentials('server-credentials')
//     // }
//     parameters {
//         choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'description')
//         booleanParam(name: 'executeTests', defaultValue: true, description: 'execute tests')
//     }
//     stages {
//         stage('init') {
//             steps {
//                 script {
//                     gv = load 'script.groovy'
//                 }
//             }
//         }
//         stage('build') {
//             steps {
//                 script {
//                     gv.buildApp()
//                 }
//             }
//         }
//         stage('test') {
//             when {
//                 expression {
//                     params.executeTests
//                 }
//             }
//             steps {
//                 script {
//                     gv.testApp()
//                 }
//             }
//         }
//         stage('deploy') {
//             // input {
//             //     message "Select the environment to deploy to"
//             //     ok "Done"
//             //     parameters {
//             //         choice(name: 'ONE', choices: ['dev', 'staging', 'prod'], description: 'Select environment')
//             //         choice(name: 'TWO', choices: ['dev', 'staging', 'prod'], description: 'Select environment')
//             //     }
//             // }
//             steps {
//                 script {
//                     env.ENV = input message: "Select the environment to deploy to", ok: "Done", parameters: [
//                         choice(name: 'ONE', choices: ['dev', 'staging', 'prod'], description: ''), 
//                         // choice(name: 'TWO', choices: ['dev', 'staging', 'prod'], description: 'Select environment')
//                     ]    

//                     gv.deployApp()
//                     // echo "deploying to ${ONE} environment"
//                     // echo "deploying to ${TWO} environment"

//                     echo "deploying to ${ENV} environment"
//                 }

//                 // echo 'deploying the application...'
//                 // echo "deploying version ${params.VERSION}"
//                 // // // Option 1:
//                 // // //  echo "deploying with ${SERVER_CREDENTIALS}"
//                 // // //  sh "${SERVER_CREDENTIALS}"

//                 // // // Option 2:
//                 // // withCredentials([
//                 // //     usernamePassword(
//                 // //         credentials: 'server-credentials', 
//                 // //         usernameVariable: 'USER',
//                 // //         passwordVariable: 'PWD'
//                 // //     )
//                 // // ]){
//                 // //     echo "deploying with ${USER} and ${PWD}"
//                 // //     sh "some script ${USER} ${PWD}"
//                 // // }

//             }
//         }
//     }
// }





// ---------- original code from nana gitlab ----------
// def gv

// pipeline {
//     agent any
//     tools {
//         maven 'maven-3.9'
//     }
//     stages {
//         stage('increment version') {
//             steps {
//                 script {
//                     echo 'incrementing app version...'
//                     sh 'mvn build-helper:parse-version versions:set \
//                         -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
//                         versions:commit'
//                     def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
//                     def version = matcher[0][1]
//                     env.IMAGE_NAME = "$version-$BUILD_NUMBER"
//                 }
//             }
//         }
//         stage('build app') {
//             steps {
//                 script {
//                     echo 'building the application...'
//                     sh 'mvn clean package'
//                 }
//             }
//         }
//         stage('build image') {
//             steps {
//                 script {
//                     echo "building the docker image..."
//                     withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
//                         sh "docker build -t nanatwn/demo-app:${IMAGE_NAME} ."
//                         sh 'echo $PASS | docker login -u $USER --password-stdin'
//                         sh "docker push nanatwn/demo-app:${IMAGE_NAME}"
//                     }
//                 }
//             }
//         }
//         stage('deploy') {
//             steps {
//                 script {
//                     echo 'deploying docker image...'
//                 }
//             }
//         }
//         stage('commit version update'){
//             steps {
//                 script {
//                     withCredentials([usernamePassword(credentialsId: 'gitlab-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
//                         sh 'git config --global user.email "jenkins@example.com"'
//                         sh 'git config --global user.name "jenkins"'

//                         sh 'git status'
//                         sh 'git branch'
//                         sh 'git config --list'

//                         // sh "git remote set-url origin https://${USER}:${PASS}@gitlab.com/twn-devops-bootcamp/latest/08-jenkins/java-maven-app.git"
//                         sh "git remote set-url origin https://${USER}:${PASS}@github.com/alexhwebdev/java-maven-app.git"
//                         sh 'git add .'
//                         sh 'git commit -m "ci: version bump"'
//                         sh 'git push origin HEAD:jenkins-jobs'
//                     }
//                 }
//             }
//         }
//     }
// }



// ---------- starter code ----------
// pipeline {
//     agent any
//     tools {
//         maven 'maven-3.9'
//     }
//     stages {
//         stage('build') {
//             steps {
//                  echo 'building the application...'
//             }
//         }
//         stage('test') {
//             steps {
//                  echo 'testing the application...'
//             }
//         }
//         stage('deploy') {
//             steps {
//                  echo 'deploying the application...'
//             }
//         }
//     }
// }