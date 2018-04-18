# Android_shopList

A simple shopping list application. 
The user should be able to see the items currently on the list, add an item and an amount of that item, 
and increase/decrease that amount later.

Architecture:
The app relies on a SQLite database to save contents in, and have both a helper and
DAO class.
Amount is incremented/decremented by one for every tap. If the item amount is decremented to
zero, the entire item is removed from the list.
For every list transaction, the list contents and the total should be immediately updated.
