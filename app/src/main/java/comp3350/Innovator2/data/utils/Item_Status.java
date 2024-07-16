package comp3350.Innovator2.data.utils;

/*All items have an attribute, set default to Active, and if we remove an item
  from the Item table then we change this attribute to Deleted. We want to keep it because
  if it is Ordered it will have a reference in another table which will be used to retrieve
  orders.*/
public enum Item_Status {
    Active,
    Deleted
}
