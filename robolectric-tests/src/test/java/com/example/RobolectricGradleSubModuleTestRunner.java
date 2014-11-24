package com.example;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.res.Fs;

/**
 * A Robolectric TestRunner for running tests in a Gradle SubModule.
 * Created by jasondonmoyer on 11/24/14.
 */
public class RobolectricGradleSubModuleTestRunner extends RobolectricTestRunner {

    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;

    public RobolectricGradleSubModuleTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    protected String getAndroidManifestPath() {
        return "../deckard-gradle/app/src/main/AndroidManifest.xml";
    }

    protected String getResPath() {
        return "../deckard-gradle/app/src/main/res";
    }

    @Override
    protected final AndroidManifest getAppManifest(Config config) {
        return new AndroidManifest(Fs.fileFromPath(getAndroidManifestPath()),
                Fs.fileFromPath(getResPath())) {
            @Override
            public int getTargetSdkVersion() {
                return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC;
            }
        };
    }

}
