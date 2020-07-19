package org.pawaneuler.Experimentation.ProgramExecution.Experimental;

import org.junit.Test;
import org.pawaneuler.Experimentation.ProgramExecution.ProgramExecutionExperiment;
import org.pawaneuler.Generator.AssociationRuleGenerator.ExperimentalMultiThreadFrequency.MultiThreadedAssocationRules;

public class MultiThreadedExperiment extends ProgramExecutionExperiment {
    @Test
    @Override
    public void stressTest() {
        super.stressTest();
    }

    @Override
    protected void GenerateAssosiationRule() {
        long startTime = System.currentTimeMillis();
                
        MultiThreadedAssocationRules generator = new MultiThreadedAssocationRules(this.testTrie, this.minSup);
        generator.execute();

        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        this.record.append(Long.toString(runTime) + ", ");
    }
}