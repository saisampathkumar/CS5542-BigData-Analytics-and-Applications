In Class Programming 5

DATA SET
Downloaded data from Caltech101 Dataset Sample 
(http://www.vision.caltech.edu/Image_Datasets/Caltech101/)

Used following images:
CUP (57 images)
Electric_Guitar (75 images)
Lamp (61 images)
Laptop (81 images)
umbrella (75 images)
watch (239 images)

For Training We used First half of the downloaded data
For Testing We used Second half of the downloaded data

Trained Classification and Retrain Inspection Final Layer Model
using training data resulted in the accuracy of 100%

Tested the trained model using test data resulted in the accuracy of 
Cup: 97%
Electric Guitar: 99%
Lamp: 96%

Used following packages
tensorflow
numpy

Using TensorBoard we have displayed the output data in the form graph, histogram
and distribution.
using following command in the terminal we displayed the output:
tensorboard --logdir="path of retrain_logs in caltech101"

