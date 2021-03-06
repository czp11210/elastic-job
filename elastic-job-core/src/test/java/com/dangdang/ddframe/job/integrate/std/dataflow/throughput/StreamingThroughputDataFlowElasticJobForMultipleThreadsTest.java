/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.job.integrate.std.dataflow.throughput;

import com.dangdang.ddframe.job.integrate.AbstractBaseStdJobAutoInitTest;
import com.dangdang.ddframe.job.integrate.WaitingUtils;
import com.dangdang.ddframe.job.integrate.fixture.dataflow.throughput.StreamingThroughputDataFlowElasticJob;
import com.dangdang.ddframe.job.internal.statistics.ProcessCountStatistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public final class StreamingThroughputDataFlowElasticJobForMultipleThreadsTest extends AbstractBaseStdJobAutoInitTest {
    
    public StreamingThroughputDataFlowElasticJobForMultipleThreadsTest() {
        super(StreamingThroughputDataFlowElasticJob.class);
    }
    
    @Before
    @After
    public void reset() {
        StreamingThroughputDataFlowElasticJob.reset();
    }
    
    @Test
    public void assertJobInit() {
        while (!StreamingThroughputDataFlowElasticJob.isCompleted()) {
            WaitingUtils.waitingShortTime();
        }
        assertTrue(getRegCenter().isExisted("/" + getJobName() + "/execution"));
        assertTrue(ProcessCountStatistics.getProcessSuccessCount(getJobName()) >= 10);
        assertThat(ProcessCountStatistics.getProcessFailureCount(getJobName()), is(0));
    }
}
