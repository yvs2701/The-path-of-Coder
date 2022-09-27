# PyMail
A simple email client made using Python.
- can send emails
- can save drafts
- more coming up...

### Requirements
- Python
- pip
- MySQL
## Using
Run the main.py inside PyMail subdirectory after installing the dependencies (read Installation guide first) by executing the following command:
```
python main.py
```
## Installation
The app's installation process is quite easy. Simply clone this github repo from your terminal or press download as zip, 
and then unzip the downloaded folder to the directory of your choice.
<br>
<img src="./install guide - 1">

Then navigate to the the directory and *inside the PyMail subdirectory*, open your terminal and type:
```
pip install -r requirements.txt
```

You can even install this app inside a virtual environment. Proceed by creating and activating a virtual environment (explained below) and 
then run the above command inside the the project directory.


### Creating a Virtual Environment:
- Make sure you have `pip` installed on your OS, then download **virtualenv**:
  ```
  pip install virtualenv
  ```
- Then create a virtual environment, copy paste the code in your terminal:
  ```
  virtualenv venv
  ```
- Now activate this virtual environment
  - On Mac OS/Linux:
    ```
    source venv/bin/activate
    ```
  - On Windows:
    ```
    venv\Scripts\activate
    ```
