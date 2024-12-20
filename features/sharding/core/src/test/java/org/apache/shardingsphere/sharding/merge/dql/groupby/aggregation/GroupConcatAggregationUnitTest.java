/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sharding.merge.dql.groupby.aggregation;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class GroupConcatAggregationUnitTest {
    
    @Test
    void assertGroupConcatAggregation() {
        GroupConcatAggregationUnit groupConcatAggregationUnit = new GroupConcatAggregationUnit(" ");
        groupConcatAggregationUnit.merge(null);
        groupConcatAggregationUnit.merge(Collections.singletonList(null));
        groupConcatAggregationUnit.merge(Collections.singletonList("001"));
        groupConcatAggregationUnit.merge(Collections.singletonList("002"));
        groupConcatAggregationUnit.merge(Collections.singletonList("002 003"));
        assertThat(groupConcatAggregationUnit.getResult(), is("001 002 002 003"));
    }
    
    @Test
    void assertDistinctGroupConcatAggregation() {
        DistinctGroupConcatAggregationUnit distinctGroupConcatAggregationUnit = new DistinctGroupConcatAggregationUnit(" ");
        distinctGroupConcatAggregationUnit.merge(null);
        distinctGroupConcatAggregationUnit.merge(Collections.singletonList(null));
        distinctGroupConcatAggregationUnit.merge(Collections.singletonList(""));
        distinctGroupConcatAggregationUnit.merge(Collections.singletonList("001"));
        distinctGroupConcatAggregationUnit.merge(Collections.singletonList("001"));
        distinctGroupConcatAggregationUnit.merge(Collections.singletonList("003"));
        assertThat(distinctGroupConcatAggregationUnit.getResult(), is(" 001 003"));
    }
}
