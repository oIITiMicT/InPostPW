package com.example.InPostPW.getParcelStatusUS;
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

public class GetParcelStatusLiveTest extends JUnitStories {
    @Override
    public Configuration configuration() {
        try {
            return new MostUsefulConfiguration()
                    .useStoryLoader(new LoadFromClasspath(this.getClass()))
                    .useStoryReporterBuilder(new StoryReporterBuilder()
                            .withCodeLocation(new File("./target/jbehave/parcel-status/foo").toURI().toURL())
                            .withFormats(TXT, STATS, XML));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new GetParcelStatusSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return List.of("getParcelStatus.story");
    }
}
