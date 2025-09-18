pipeline {
    agent any

    environment {
        NODE_HOME = "C:\\Program Files\\nodejs"
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-21"
        PATH = "${env.NODE_HOME};${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    parameters {
        string(name: 'BRANCH', defaultValue: 'main', description: 'Git branch to build')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/sam1212111/CI-CD-Project'
                echo "✅ Checked out branch: ${params.BRANCH}"
            }
        }
stage('Build Frontend') {
    steps {
        dir('frontend') {
            // Check Node and npm versions
                    bat 'npm install'
                    bat 'npm run build'
                    echo "✅ Frontend built successfully."
        }
    }
}
stage('Run Selenium Tests') {
    steps {
        dir('SeleniumTests') {
            // Compile all Java test files
            bat 'javac -cp "lib/*" -d bin *.java'
            
            // Run the JUnit 5 test using ConsoleLauncher
            bat 'java -cp "lib/*;bin" org.junit.platform.console.ConsoleLauncher -c TestTodoApp'
            
            echo "✅ Selenium tests executed against deployed backend."
        }
    }
}


        stage('Deploy Frontend (Optional)') {
            steps {
                echo "🚀 Deploy frontend to hosting (configure your deployment commands here if needed)"
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully for branch: ${params.BRANCH}"
        }
        failure {
            echo "❌ Pipeline failed for branch: ${params.BRANCH}"
        }
    }
}
