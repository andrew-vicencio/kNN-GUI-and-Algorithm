# **project-coders-inc**

This code was created for our Third Year Project class - SYSC 3110.

## Authors
* Benjamin Bichel
* Darren Holden
* Gabrielle Hubert
* Andrew Vicencio

## Deliverables
* Controllers
	* ButtonAddFeaturesController.java
	* ButtonMenuController.java
	* FeaturePanelController.java
	* FeaturePanelComplexController.java
	* FeaturePanelSimpleController.java
	* MainController.java
	* SaveController.java
	* ValueInputController.java
	* ValuePromptFrameController.java
	* ValueTestFrameController.java
* DataModel	
	* Cell.java
	* CellComposite.java
	* CellSimple.java
	* DimensionalSpace.java
	* Point.java
* ImportExport
	* SerialExport.java
	* SerialImport.java
	* test.txt
* Maths
	* ChebyshevKNN.java
	* DistanceAlg.java
	* EuclideanKNN.java
	* KNN.java
	* ManhattanKNN.java
	* MinkowskiKNN.java
	* NumericalDistance.java
	* NumericalDifference.java
	* NumericalEquality.java
	* NumericalStdDev.java
	* StringCharacterValue.java
	* StringDistance.java
	* StringEquality.java
	* StringHamming.java
* Tests
	* MyTests.java
	* TestEnvironment.java
* View
	* FeaturePanel.java
	* FeaturePanelComplex.java
	* FeaturePanelSimple.java
	* PromptFrame.java
	* PromptSaveFrame.java
	* PromptValueFrame.java
	* TestCaseFrame.java
	* View.java
* UML file
* javadoc
* User Manual


## Changes Since Last Milestone
* Added support for multiple test cases. Now, the user can run a suite a tests bu clicking the "Add Test" button. Clicking "Done" will provide the user with total results, including an overall success rate.
* The user can now save and load a set of data
* The user can now choose distance metrics for individual features.
	* Difference (available for ints and floats)
	* Equality (available for ints and floats)
	* Standard Deviation (available for ints and floats)
	* Hamming (available for Strings)
	* Equal (available for Strings)
	* Character Value (available for Strings)

## Known Issues
* Innacurate results (See test screen shots in Files)
* When entering a test case, if the user decides to quit on one of the prompts, the next prompt frames will still appear.

## Future Steps


