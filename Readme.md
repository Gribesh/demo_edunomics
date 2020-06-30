# Edunomics Assignment Documentation

## (Mobile App Development using Android)

# Table of Contents

[ Objective ](#Objective)

[Abstract](#Abstract)

[Introduction](#Introduction)

[Features](#Features)

[Testing_Result](#Testing)

[Conclusion](#Conclusion)

[Future_Work](#Future)

<a name="Objective"></a>

# Objective:

Create a mobile application that should implement a login feature.
After the log in,some implement a chat app.
The app must also consist of an autocomplete search box, put the search box anywhere a search feature can be implemented like searching through chats or user names.
App must be compatible with the UI of our website :https://edunomics.in/

<a name="Abstract"></a>

# Abstract:

The main objective of this documentation is to present a software application for the login and logout use case for this a parse server as backend is used. The application developed for android will enable the new users to signup as well as registered users can log in and chat with the users connected to that parse server. The system requires devices to be connected via the internet. Java is used as a programming language and Bitnami Parse Server is hosted on AWS.

<a name="Introduction"></a>

# Introduction

This is a simple android mobile application where a new user can create a new profile using signup page or previously registered user can log in.They can chat with other users in realtime and for ease search option has been implemented.

<a name="Features"></a>

# Features

## Login:

Input: username , password (valid)

Output:

    If credentials matches

    	Redirect to Home Page
    Else
    	Error message is displayed

## SignUp:

Input: username , password, confirm password, date of birth, phone number
Output:

    If (username is unique) && (password is valid) && (password==confirm password)
    	Signup the user
    	Redirect to Home page
    Else
    	Error message is displayed

## Logout:

Input: Press Logout from option menu
Output:

    If there is current user
    	Logout
    	Redirect to login page

## ShowPassword

Input: Click
Output:

    If checked:
    	Show password
    Else:
    	Hide password

## Search

Input: Key value
Output:

    If username found:
    	Show username
    Else:
    	NULL

Autocomplete has been implemented while searching.

<a name="Testing"></a>

## Testing Result

- Username and password shouldn’t be blank.
- Passwords should meet the requirement.
  - Minimum 8 letters
  - At least 1 digit
  - At least 1 lower case letter
  - At least 1 upper case letter
  - No white spaces
  - At least 1 special character
- Password should match with confirm password.

#### Username and Password for Login:

Username: demo1

Password: abc123@A

#### For signup use any username but valid password:

Example password: xyz@123A , ijk#\$12JK

<a name="Conclusion"></a>

# Conclusion

We can implement authentication using a parse server as a backend conveniently with our android application. It can also be used to store data and files as per our need.

<a name="Future"></a>

# Future Work

Improvement in UI.
Addition of content in home page.

Github Link : https://github.com/Gribesh/demo_edunomics.git
