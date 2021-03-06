/*
 * Copyright 2017 the original author or authors.
 *
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
 */

package io.pivotal.spring.cloud.jose.inbound;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TokenPolicyChecker implements PolicyChecker {
	private final TokenPolicy tokenPolicy;

	@Override
	public void checkPolicy(Map<String, Object> initialTokenClaims, List<SelfIssuedToken> callStack)
			throws PolicyException {
		try {
			tokenPolicy.apply(initialTokenClaims, callStack);
		} catch (PolicyException e) {
			log.error("invocation did not adhere to policy", e);
			throw e;
		}
	}
	
}
