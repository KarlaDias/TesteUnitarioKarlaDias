def userdir = '/home/karla'

node {
stage('Build') {
echo 'Building..'
deleteDir()
checkout scm
sh 'echo "res" > result'
stash includes: '**/result', name: 'app'
}
stage('Test') {
echo 'Testing..'
try {
sh "sudo docker run -v ${userdir}/devops/exercicio3/srv/jenkins/workspace/${env.JOB_NAME}:/workspace -w /workspace maven:latest mvn clean install" 
echo 'Sucesso!'
}
catch (e) {
echo 'Falhou.'
throw e
}
finally {
unstash 'app'
sh 'cat result'
}
}
stage('Deploy') {
echo 'Deploying....'
}
}