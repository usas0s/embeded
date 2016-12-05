
/**
 * Created by MrLEE on 2016-12-05.
 */

package com.example.mju.embeded;

        import android.app.IntentService;
        import android.content.Intent;
        import android.content.Context;
        import android.text.TextUtils;
        import android.widget.Toast;

        import com.google.android.gms.location.Geofence;
        import com.google.android.gms.location.GeofencingEvent;

        import java.util.ArrayList;
        import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GeofenceTransitionsIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.mju.embeded.action.FOO";
    private static final String ACTION_BAZ = "com.example.mju.embeded.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.mju.embeded.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.mju.embeded.extra.PARAM2";

    public GeofenceTransitionsIntentService() {
        super("GeofenceTransitionsIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, GeofenceTransitionsIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, GeofenceTransitionsIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);

        List<Geofence> triggeringGeofences = event.getTriggeringGeofences();
        ArrayList triggeringGeofencesIdsList = new ArrayList();
        for(Geofence geofence : triggeringGeofences)
        {
            triggeringGeofencesIdsList.add(geofence.getRequestId());
        }
        String IDs = TextUtils.join(", ", triggeringGeofencesIdsList);

        int transitiontype = event.getGeofenceTransition();
        if(transitiontype == Geofence.GEOFENCE_TRANSITION_ENTER)
        {
            Toast.makeText(getApplicationContext(),"entering " + IDs,Toast.LENGTH_LONG).show();
        }
        if(transitiontype == Geofence.GEOFENCE_TRANSITION_EXIT)
        {
            Toast.makeText(getApplicationContext(),"entering" + IDs,Toast.LENGTH_LONG).show();
        }


        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
