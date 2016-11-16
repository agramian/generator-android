package <%= app_id %>.cache;


import <%= app_id %>.model.User;

import de.devland.esperandro.SharedPreferenceMode;
import de.devland.esperandro.annotations.SharedPreferences;

@SharedPreferences(name = "userPrefs", mode = SharedPreferenceMode.PRIVATE)
public interface UserCache {

    User user();

    void user(User user);

}
