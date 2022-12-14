package com.example.InPostPW.registrationUS;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;
import static org.jbehave.core.reporters.Format.XML;


public class RegistrationStoryLiveTest extends JUnitStories {

    ObjectMapper mapper = new ObjectMapper();


    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(getClass()))
                        .withFormats(CONSOLE));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new RegistrationSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return List.of("registration.story");
    }
}
