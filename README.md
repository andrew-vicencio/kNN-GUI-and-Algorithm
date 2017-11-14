# **project-coders-inc**

## Authors
* Benjamin Bichel
* Darren Holden
* Gabrielle Hubert
* Andrew Vicencio

## Deliverables
* Cell.java
* CompositeCell.java
* DimensionalSpace.java
* DistanceAlg.java
* DoneButtonController.java
* EuclideanKNN.java
* FeaturePanelComplex.java
* FeaturePanelComplexController.java
* FeaturePanelSimple.java
* FeaturePanelSimpleController.java
* KNN.java
* MainController.java
* MenuController.java
* NumericalDistance.java
* Point.java
* PromptValueFrame.java
* PromptValueFrameController.java
* SimpleCell.java
* StringDistance.java
* TestCaseFrame.java
* TestCaseFrameController.java
* TestEnvironment.java
* View.java
* UML file
* javadoc
* User Manual


## Changes Since Last Milestone
* Created a GUI
* Features can now have one of three primitive types: int, float and String
* Allowed test cases to be used to predict any features
* Increased cohesion with new classes for distance algorithms
* Support for composite/complex features


## Known Issues
* There is a lot of code duplication between these pairs of classes:
** FeaturePanelComplex and FeaturePanelSimple
** FeaturePanelComplexController and FeaturePanelSimpleController
** PromptValueFrame and TestCaseFrame
* Some aspects of the UI do not have ease of use, such as repeatedly clicking "Add Value" and "Add Simple Feature" and "Add Complex Feature" in the menu bar
* No frames scroll to accomadate for extra panels
* Points/values are not editable or removable once added
* Points/values are not very readable when displayed

## Future Steps
* Redesign some aspects to improve cohesion and create looser coupling
* Make the program more user-friendly
* Increase flexibility (to be clarified at a later date)
* Implement save/load functionality
