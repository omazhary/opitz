package dbconns;

import java.util.ArrayList;

public interface IDBConn {
	
	public ArrayList<String[]> runSelectQuery(String query);

}
