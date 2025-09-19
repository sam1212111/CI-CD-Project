# 🚀 CI/CD Automation Project with Jenkins & Selenium

![CI/CD](https://img.shields.io/badge/CI/CD-Jenkins-blue) ![GitHub](https://img.shields.io/badge/GitHub-Repository-black) ![React](https://img.shields.io/badge/Frontend-React-blueviolet) ![Selenium](https://img.shields.io/badge/Testing-Selenium-orange)

---

## Project Overview

This project demonstrates a **full CI/CD pipeline** using **Jenkins** integrated with **GitHub Webhooks**, a **React frontend**, and **Selenium tests**.  

The pipeline automates:

- Triggering builds on **GitHub commits**
- Building the **React frontend**
- Running **Selenium UI tests**
- Optional deployment preparation

> ⚠️ Note: Pipeline runs internally in Jenkins; no public deployment URL is included.  

---

## Features

- **Automated Build:** React frontend builds triggered on code push
- **Automated Testing:** Selenium UI tests executed automatically after build
- **Branch-based Builds:** Configured for feature and main branches
- **Environment Management:** NodeJS and Java environment variables set in Jenkins
- **Extensible Deployment:** Deployment stage ready for future integration

---

## Project Structure

CI-CD-Project/
│
├─ backend/ # Flask backend (API layer, no automated tests included)
├─ frontend/ # React frontend application
├─ SeleniumTests/ # Selenium test cases for UI automation
├─ Jenkinsfile # Declarative Jenkins pipeline definition
├─ README.md # Project documentation
└─ .gitignore

---

## Prerequisites

- **Jenkins** installed with **Pipeline plugin**
- **Node.js** and **npm** installed
- **Java JDK** installed (for Selenium tests)
- **Git** installed and configured
- React frontend project initialized with `create-react-app`
- Selenium tests structured in Java with JUnit

---

## Setup Instructions

### 1. Clone Repository
Clone the project from GitHub:

git clone <repository-url>
cd CI-CD-Project


### 2. Configure Jenkins

- Install required plugins: **Git**, **Pipeline**, **NodeJS**, **JUnit**
- Add **NodeJS** and **Java** installations in Jenkins global tool configuration
- Create a **Pipeline Job** and point it to this repository
- Configure **branch to build** (default: `feature` or `main`)
- Enable **GitHub webhook trigger** for automatic builds on commits

### 3. Configure Webhooks

- In GitHub, go to repository **Settings → Webhooks**
- Add webhook pointing to Jenkins:  
http://<jenkins-server>/github-webhook/

- Ensure webhook events are set to **push events** only

---

## How to Run the Pipeline

1. Push code to the configured GitHub branch
2. Jenkins will detect the push via webhook
3. Pipeline stages executed automatically:

 - **Checkout:** Pulls latest code from GitHub
 - **Build Frontend:** Installs dependencies and builds React app
 - **Run Selenium Tests:** Executes UI automation
 - **Optional Deploy:** Placeholder for future deployment integration

4. View pipeline results in Jenkins console

---

## Workflow Overview

```text
GitHub Push
   │
   ▼
Webhook Trigger
   │
   ▼
Jenkins Pipeline
┌──────────────┐
│ Checkout     │
│ Build Frontend│
│ Run Tests    │
│ Deploy       │
└──────────────┘
   │
   ▼
Notifications & Logs
Notes
Currently, the backend is not tested automatically

Selenium tests are focused on frontend UI

Pipeline ensures real-time testing for every commit

Deployment stage is optional and can be extended

Technology Stack
Layer	Technology
CI/CD	Jenkins
Version Control	Git/GitHub
Frontend	React
Testing	Selenium, JUnit
Backend	Python Flask
Node Package Manager	npm

Key Learnings
Implemented branch-based automated builds

Configured Jenkins environment variables for Node and Java

Integrated Selenium test automation in CI/CD

Gained practical knowledge of webhooks and automated triggers

Author
Sanat  |  Open to automation & cloud-native projects