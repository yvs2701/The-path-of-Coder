import joblib
import numpy as np
from sklearn.metrics import accuracy_score, classification_report
from sklearn.model_selection import train_test_split

if __name__ == "__main__":
    with_mask = np.load('test/data/with_mask.npy')
    row_with_mask, a, b, c = with_mask.shape
    with_mask = with_mask.reshape(row_with_mask, a*b*c)

    without_mask = np.load('test/data/without_mask.npy')
    row_without_mask, a, b, c = without_mask.shape
    without_mask = without_mask.reshape(row_without_mask, a*b*c)

    wrong_mask = np.load('test/data/wrong_mask.npy')
    row_wrong_mask, a, b, c = wrong_mask.shape
    wrong_mask = wrong_mask.reshape(row_wrong_mask, a*b*c)

    X = np.r_[with_mask, without_mask, wrong_mask] # merge them row wise
    labels = np.zeros(X.shape[0])
    # divide the array in with mask (0) and without mask
    labels[row_with_mask:] = 1 # all the rows from from last row_with_mask to end is rows without mask
    labels[row_with_mask + row_wrong_mask:] = 2 # all the rows from from last row_with_mask to end is rows under the nose
    rectangle_label = {0: 'Wearing Mask', 1: 'No Mask', 2: 'Under the Nose'}

    print(row_with_mask, row_without_mask, row_wrong_mask)
    np.save('test/data/with_mask.npy', with_mask[:1400])
    np.save('test/data/without_mask.npy', without_mask[:1400])

    x_train, x_test, y_train, y_test = train_test_split(X, labels, test_size=0.2) # 20% data for testing

    svm = joblib.load('model/svm_model.pkl')
    y_pred = svm.predict(x_test)
    
    accuracy = accuracy_score(y_test, y_pred)
    print(classification_report(y_true=y_test, y_pred=y_pred))
