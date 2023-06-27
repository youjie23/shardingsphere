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

package org.apache.shardingsphere.infra.metadata.nodepath;

import lombok.Getter;
import org.apache.shardingsphere.infra.metadata.nodepath.item.NamedRuleItemNodePath;
import org.apache.shardingsphere.infra.metadata.nodepath.item.UniqueRuleItemNodePath;
import org.apache.shardingsphere.infra.metadata.nodepath.root.RuleRootNodePath;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Rule node path.
 */
public final class RuleNodePath {
    
    @Getter
    private final RuleRootNodePath rootNodePath;
    
    private final Map<String, NamedRuleItemNodePath> namedRuleItemNodePaths;
    
    private final Map<String, UniqueRuleItemNodePath> uniqueRuleItemNodePaths;
    
    public RuleNodePath(final String ruleType, final Collection<String> namedRuleItemNodePathTypes, final Collection<String> uniqueRuleItemNodePathTypes) {
        rootNodePath = new RuleRootNodePath(ruleType);
        namedRuleItemNodePaths = getNamedRuleItemNodePathMap(namedRuleItemNodePathTypes);
        uniqueRuleItemNodePaths = getUniqueRuleItemNodePathMap(uniqueRuleItemNodePathTypes);
    }
    
    private Map<String, NamedRuleItemNodePath> getNamedRuleItemNodePathMap(final Collection<String> namedRuleItemNodePathTypes) {
        Map<String, NamedRuleItemNodePath> result = new HashMap<>(namedRuleItemNodePathTypes.size(), 1F);
        for (String each : namedRuleItemNodePathTypes) {
            result.put(each, new NamedRuleItemNodePath(rootNodePath, each));
        }
        return result;
    }
    
    private Map<String, UniqueRuleItemNodePath> getUniqueRuleItemNodePathMap(final Collection<String> uniqueRuleItemNodePathTypes) {
        Map<String, UniqueRuleItemNodePath> result = new HashMap<>(uniqueRuleItemNodePathTypes.size(), 1F);
        for (String each : uniqueRuleItemNodePathTypes) {
            if (each.contains(".")) {
                String[] values = each.split("\\.");
                result.put(values[1], new UniqueRuleItemNodePath(rootNodePath, values[0], values[1]));
            } else {
                result.put(each, new UniqueRuleItemNodePath(rootNodePath, each));
            }
        }
        return result;
    }
    
    /**
     * Get named rule item node path.
     * 
     * @param itemType item type
     * @return named rule item node path
     */
    public NamedRuleItemNodePath getNamedRuleItemNodePath(final String itemType) {
        return namedRuleItemNodePaths.get(itemType);
    }
    
    /**
     * Get unique rule item node path.
     *
     * @param itemType item type
     * @return unique rule item node path
     */
    public UniqueRuleItemNodePath getUniqueRuleItemNodePaths(final String itemType) {
        return uniqueRuleItemNodePaths.get(itemType);
    }
}