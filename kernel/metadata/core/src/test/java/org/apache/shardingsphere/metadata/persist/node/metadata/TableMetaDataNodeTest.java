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

package org.apache.shardingsphere.metadata.persist.node.metadata;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableMetaDataNodeTest {
    
    @Test
    void assertGetMetaDataTablesNode() {
        assertThat(TableMetaDataNode.getMetaDataTablesNode("foo_db", "foo_schema"), is("/metadata/foo_db/schemas/foo_schema/tables"));
    }
    
    @Test
    void assertGetTableActiveVersionNode() {
        assertThat(TableMetaDataNode.getTableActiveVersionNode("foo_db", "foo_schema", "foo_tbl"), is("/metadata/foo_db/schemas/foo_schema/tables/foo_tbl/active_version"));
    }
    
    @Test
    void assertGetTableVersionsNode() {
        assertThat(TableMetaDataNode.getTableVersionsNode("foo_db", "foo_schema", "foo_tbl"), is("/metadata/foo_db/schemas/foo_schema/tables/foo_tbl/versions"));
    }
    
    @Test
    void assertGetTableVersionNode() {
        assertThat(TableMetaDataNode.getTableVersionNode("foo_db", "foo_schema", "foo_tbl", "0"), is("/metadata/foo_db/schemas/foo_schema/tables/foo_tbl/versions/0"));
    }
    
    @Test
    void assertGetTableNode() {
        assertThat(TableMetaDataNode.getTableNode("foo_db", "foo_schema", "foo_tbl"), is("/metadata/foo_db/schemas/foo_schema/tables/foo_tbl"));
    }
    
    @Test
    void assertGetTableNameByActiveVersionNode() {
        Optional<String> actual = TableMetaDataNode.getTableNameByActiveVersionNode("/metadata/foo_db/schemas/foo_schema/tables/foo_tbl/active_version");
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is("foo_tbl"));
    }
    
    @Test
    void assertGetTableNameByActiveVersionNodeIfNotFound() {
        assertFalse(TableMetaDataNode.getTableNameByActiveVersionNode("/xxx/foo_db/schemas/foo_schema/tables/foo_tbl/active_version").isPresent());
    }
    
    @Test
    void assertGetTableName() {
        Optional<String> actual = TableMetaDataNode.getTableName("/metadata/foo_db/schemas/foo_schema/tables/foo_tbl");
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is("foo_tbl"));
    }
    
    @Test
    void assertGetTableNameIfNotFound() {
        assertFalse(TableMetaDataNode.getTableName("/xxx/foo_db/schemas/foo_schema/tables/foo_tbl").isPresent());
    }
    
    @Test
    void assertIsTableActiveVersionNode() {
        assertTrue(TableMetaDataNode.isTableActiveVersionNode("/metadata/foo_db/schemas/foo_schema/tables/foo_tbl/active_version"));
    }
    
    @Test
    void assertIsTableNode() {
        assertTrue(TableMetaDataNode.isTableNode("/metadata/foo_db/schemas/foo_schema/tables/foo_tbl"));
    }
}
