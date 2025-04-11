# 🔐 Spring Boot OAuth 2.0 Login (Google & GitHub)

This project demonstrates how to implement **OAuth 2.0 login** in a **Spring Boot** application using **Google** and **GitHub** as authentication providers.

---

## 📌 What is OAuth 2.0?

**OAuth 2.0** is an open standard for token-based authentication and authorization. It allows third-party services like Google and GitHub to authenticate users without sharing credentials.

In this app:
- Users can log in via their GitHub or Google accounts.
- Spring Security handles the OAuth2 flow and session management.

---

## 🚀 Spring Boot + OAuth2 Integration

Spring Boot simplifies the integration with OAuth2 providers using the `spring-boot-starter-oauth2-client` dependency.

The configuration allows:
- Registering multiple OAuth2 providers (Google, GitHub, etc.)
- Custom login pages
- Redirecting users after successful login
- Secure session and logout support

---

## ⚙️ GitHub App Configuration

To enable GitHub login:

1. Go to [GitHub Developer Settings](https://github.com/settings/developers).
2. Click **"New OAuth App"**.
3. Fill in:
    - **Application name**: Your app name
    - **Homepage URL**: `http://localhost:8080`
    - **Authorization callback URL**: `http://localhost:8080/login/oauth2/code/github`
4. Click **Register application**.
5. Copy the **Client ID** and **Client Secret**.
6. Add them to your Spring Boot application configuration.

---

## ☁️ Google Cloud OAuth App Configuration

To enable Google login:

1. Visit [Google Cloud Console](https://console.cloud.google.com/).
2. Create or select a project.
3. Navigate to **APIs & Services > OAuth consent screen**:
    - Choose **External** user type
    - Fill in app details (name, support email, etc.)
    - Add scopes: `email`, `profile`
    - Add test users (your Gmail account)
4. Go to **Credentials > Create Credentials > OAuth client ID**
    - App type: **Web application**
    - Add **Authorized redirect URI**: `http://localhost:8080/login/oauth2/code/google`
5. Click **Create**.
6. Copy the **Client ID** and **Client Secret**.
7. Add them to your Spring Boot application configuration.

---

Now you’re all set to use Google and GitHub for secure login in your Spring Boot app!
