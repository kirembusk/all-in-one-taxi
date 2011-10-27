package my.apps;

import com.google.gwt.core.client.JavaScriptObject;

class TaxiDriverData extends JavaScriptObject {                              // (1)
  // Overlay types always have protected, zero argument constructors.
  protected TaxiDriverData() {}                                              // (2)

  // JSNI methods to get driver data.
  public final native String getId() /*-{ return this.id; }-*/; // (3)
  public final native double getName() /*-{ return this.name; }-*/;
  public final native double getPhoneNumber() /*-{ return this.phoneNumber; }-*/;

 
}

