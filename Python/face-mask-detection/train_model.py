import joblib
import numpy as np
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split

if __name__ == "__main__":
    with_mask = np.load('data/with_mask.npy')
    row_with_mask, a, b, c = with_mask.shape
    with_mask = with_mask.reshape(row_with_mask, a*b*c)

    without_mask = np.load('data/without_mask.npy')
    row_without_mask, a, b, c = without_mask.shape
    without_mask = without_mask.reshape(row_without_mask, a*b*c)

    X = np.r_[with_mask, without_mask] # merge them row wise
    labels = np.zeros(X.shape[0])
    # divide the array in with mask (0) and without mask
    labels[row_with_mask:] = 1 # all the rows from from last row_with_mask to end is rows without mask
    rectangle_label = {0: 'Wearing Mask', 1: 'No Mask'}

    x_train, x_test, y_train, y_test = train_test_split(X, labels, test_size=0.2) # 20% data for testing

    svm = SVC()
    svm.fit(x_train, y_train)
    y_pred = svm.predict(x_test)
    
    accuracy = accuracy_score(y_test, y_pred)
    print(accuracy)
    if accuracy >= 0.90:
        joblib.dump(svm, 'model/svm_model.pkl') # save model if it is accurate enough
    else:
        raise Exception('Accuracy was less than 90%\nModel was not saved, try again :(')
