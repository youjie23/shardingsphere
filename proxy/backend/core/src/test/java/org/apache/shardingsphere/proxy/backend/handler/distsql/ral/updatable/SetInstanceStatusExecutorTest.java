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

package org.apache.shardingsphere.proxy.backend.handler.distsql.ral.updatable;

import org.apache.shardingsphere.distsql.statement.ral.updatable.SetInstanceStatusStatement;
import org.apache.shardingsphere.infra.exception.generic.UnsupportedSQLOperationException;
import org.apache.shardingsphere.infra.state.instance.InstanceState;
import org.apache.shardingsphere.mode.manager.ContextManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SetInstanceStatusExecutorTest {
    
    private final SetInstanceStatusExecutor executor = new SetInstanceStatusExecutor();
    
    @Test
    void assertExecuteUpdateWithNotExistsInstanceID() {
        assertThrows(UnsupportedSQLOperationException.class, () -> executor.executeUpdate(new SetInstanceStatusStatement("ENABLE", "instanceID"), mock(ContextManager.class, RETURNS_DEEP_STUBS)));
    }
    
    @Test
    void assertExecuteUpdateWithCurrentUsingInstance() {
        ContextManager contextManager = mock(ContextManager.class, RETURNS_DEEP_STUBS);
        when(contextManager.getComputeNodeInstanceContext().getInstance().getMetaData().getId()).thenReturn("instanceID");
        assertThrows(UnsupportedSQLOperationException.class, () -> executor.executeUpdate(new SetInstanceStatusStatement("DISABLE", "instanceID"), contextManager));
    }
    
    @Test
    void assertExecuteUpdateWithAlreadyDisableInstance() {
        ContextManager contextManager = mock(ContextManager.class, RETURNS_DEEP_STUBS);
        when(contextManager.getComputeNodeInstanceContext().getInstance().getMetaData().getId()).thenReturn("currentInstance");
        when(contextManager.getComputeNodeInstanceContext().getComputeNodeInstanceById("instanceID").isPresent()).thenReturn(true);
        when(contextManager.getComputeNodeInstanceContext().getComputeNodeInstanceById("instanceID").get().getState().getCurrentState()).thenReturn(InstanceState.CIRCUIT_BREAK);
        assertThrows(UnsupportedSQLOperationException.class, () -> executor.executeUpdate(new SetInstanceStatusStatement("DISABLE", "instanceID"), contextManager));
    }
}
