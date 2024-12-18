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

import org.apache.shardingsphere.spi.NewInstanceServiceLoader;
import org.apache.shardingsphere.sql.parser.sql.statement.SQLStatement;

import java.util.Collection;

/**
 * Sui Parsing hook for SPI.
 *
 * @author youjie_li
 */
public final class SuiSPIParsingHook implements SuiParsingHook {

    static {
        NewInstanceServiceLoader.register(SuiParsingHook.class);
    }

    private final Collection<SuiParsingHook> suiParsingHooks =
            NewInstanceServiceLoader.newServiceInstances(SuiParsingHook.class);

    @Override
    public void start(final String sql, final boolean useCache) {
        for (SuiParsingHook each : suiParsingHooks) {
            each.start(sql, useCache);
        }
    }

    @Override
    public void finishSuccess(final SQLStatement sqlStatement, final boolean useCache) {
        for (SuiParsingHook each : suiParsingHooks) {
            each.finishSuccess(sqlStatement, useCache);
        }
    }

    @Override
    public void finishFailure(final Exception cause, final boolean useCache) {
        for (SuiParsingHook each : suiParsingHooks) {
            each.finishFailure(cause, useCache);
        }
    }
}
