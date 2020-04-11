/* Created by Catalina 4/4/2020
 * 
 * 
 * 
 * 
 */
package CompleteSimStation;

import mvc.*;

public interface SimFactory extends AppFactory{

	public abstract View getView(Model m);

}
