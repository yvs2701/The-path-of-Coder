# FACE-MASK-DETECTION
This is a simple python project to detect if the user is wearing a face mask or not.

## Execution
Simply download and install the app and naviagte within the *face-mask-detection* folder and execute the **main.py** file, to get the app running.\
You can press 'q' key to quit the app.
- Before starting the app, remember to first collect some data using **collect_data.py**
  - First run **collect_data.py** and a window will open up to record you. Make sure you are not wearing mask right now !
  - After this the window will be closed, and after 10 seconds it will pop up again to record you with your mask.
- Now your data is ready you can go on execute **train_model.py** to train and save the model.
  - Note that if the model is not accurate enough you will get an error message stating the same.
  - To resolve this try adjusting your lighting and sit in a brighter room.
  - Also wearing a mask which matches your skin color a lot may make it difficult for the model to be accurate.
- Now you may execute **main.py** to see this app in action.

## Installation
The app's installation process is quite easy. Simply clone this github repo from your terminal or press download as zip, 
and then unzip the downloaded folder to the directory of your choice.

Then navigate to the the directory and inside the  CoWin_Terminal folder open your terminal and type:
```
pip install -r requirements.txt
```

You can even install this app inside a virtual environment go on and create a virtual environment (explained below) and 
then follow the same steps as explained in this section.


## Creating a Virtual Environment:
- Make sure you have `python-pip` installed, and download **virtualenv**:
  ```
  pip install virtualenv
  ```
- Then create a virtual environment (you can give any name in place of 'venv'), copy and paste the code in your terminal:
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
