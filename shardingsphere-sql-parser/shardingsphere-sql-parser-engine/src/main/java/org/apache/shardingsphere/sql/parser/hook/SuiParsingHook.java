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

package org.apache.shardingsphere.sql.parser.hook;

import org.apache.shardingsphere.sql.parser.sql.statement.SQLStatement;

/**
 * Parsing hook.
 *
 * @author zhangliang
 */
public interface SuiParsingHook {

    /**
     * Handle when parse started.
     *
     * @param sql      SQL to be parsed
     * @param useCache use cache or not
     */
    void start(String sql, boolean useCache);

    /**
     * Handle when parse finished success.
     *
     * @param sqlStatement sql statement
     * @param useCache     use cache or not
     */
    void finishSuccess(SQLStatement sqlStatement, boolean useCache);

    /**
     * Handle when parse finished failure.
     *
     * @param cause    failure cause
     * @param useCache use cache or not
     */
    void finishFailure(Exception cause, boolean useCache);
}
