package com.example.user.bakingtime;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.media.session.PlaybackStateCompat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by user on 6/17/2017.
 */
@RunWith(AndroidJUnit4.class)
public class SingleRecepieClickIntentTest  {

    private IdlingResource mIdlingResource;

    @Rule
    public IntentsTestRule<SingleRecepieActivity> mActivityRule = new IntentsTestRule<>(
            SingleRecepieActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();

        Espresso.registerIdlingResources(mIdlingResource);
    }
    @Before
    public void stubAllExternalIntents() {

        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

    }
    @Test
    public void click_to_check_ingredeint() {

        Intent intent = new Intent();
        intent.putExtra("id", 1);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);


        intending(toPackage("com.example.user.bakingtime")).respondWith(result);




        onView(withId(R.id.ingre_textview)).perform(click());


    }


}


