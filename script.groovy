def buildApp() {
    echo 'building the application...'
}
// ----------COMMENTED OUT : java-maven-pipeline, S8 L14 ----------
// def buildJar() {
//     echo 'building the application...'
//     sh 'mvn package'
// }
// ----------COMMENTED OUT : java-maven-pipeline, S8 L14 ----------
// def buildImage() {
//     echo 'building the docker image...'
//     withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
//         // sh "docker build -t nanatwn/demo-app:jma-2.0 ."
//         sh "docker build -t alexhwebdev/nana-demo-app:jma-2.0 ."
//         sh 'echo $PASS | docker login -u $USER --password-stdin'
//         // sh "docker push nanatwn/demo-app:jma-2.0"
//         sh "docker push alexhwebdev/nana-demo-app:jma-2.0"
//     }
// }

def deployApp() {
    echo 'deploying the application...'
}

def testApp() {
    echo 'testing the application...'
}

// def deployApp() {
//     echo 'deploying the application...'
//     echo "deploying version ${params.VERSION}"
// }
return this