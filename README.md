# Blank Slate
MAIN OBJECTIVE:
  Art interface (user can select different colors and pen sizes), 
  User will be able to draw in a space provided in the interface, 
  They can save the drawing to their computer, 
  Subtly animated background to make it less boring to look at the interface (required I think). 
  
FEATURES TBA (To Be Added):
  Later put in a file retrieval system and allow them to re-edit a previously made drawing, 
  Tooltips upon the first time opening the user interface to tell the user how to change colors and brush sizes, 
  Undo button for mistakes, 
  Eraser option that uses brush size tool as well, 
  Bucket option to fill an area with a color, 
  Gradient option. 
  
Potential Ways To Organize Code in the way we are "supposed to"
  Create a class for what the brush is and create a default
   the class requires a brush size and color
   default will be the medium brush size and red
  As we add more modifications we add more parts to the class that are needed to determine the output of the brush (cursor)
   potentially another class that would utilized for a linked list, with each node containing each keystroke/change on the canvas 
   traverse backwards through the list to “undo” actions within the program.
