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
<<<<<<< HEAD
* Created Cell objects
	* Holds both the raw and standardised user inputted data
	* There are two different types of Cells:
		* Simple Cells
			- Holds a simple data type
		* Composite Cells
			- Holds a reference to more data and cells
			- Can hold more complex user-defined data types
	* Provides getters and setters for all included info
* Refactored DimensionalSpace and kNN functions to use the Cell objects
* Added other algorithms to help calculate other data types, such as Strings, and Floats
* Created a GUI
* Create a Unit Test - MyTests
	* Tested the following:
		- EuclideanKNN with different data types
		- finding the sum, mean, and standard deviation of each attribute
* Allowed test cases to be used to predict any features
* Increased cohesion with new classes for distance algorithms

## Known Issues
* Testing cases are currently not working through GUI
* Code duplication between **FeaturePanelComplex and FeaturePanelSimple**, **FeaturePanelComplexController and FeaturePanelSimpleController**, and **PromptValueFrame and TestCaseFrame**
* Some aspects of the UI do not have ease of use, such as repeatedly clicking "Add Value", "Add Simple Feature", and "Add Complex Feature" in the menu bar
* No frames scroll to accomodate for extra panels
* Points/values are not editable or removable once added
* Points/values are not very readable when displayed

=======
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
>>>>>>> refs/heads/GUI

## Future Steps
* Redesign some aspects to improve cohesion and create looser coupling
* Make the program more user-friendly
<<<<<<< HEAD
* Increase flexibility
* Implement save/load functionality







=======
* Increase flexibility (to be clarified at a later date)
* Implement save/load functionality
>>>>>>> refs/heads/GUI
