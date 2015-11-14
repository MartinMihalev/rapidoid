package org.rapidoid.http.fast.handler;

/*
 * #%L
 * rapidoid-http-fast
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski and contributors
 * %%
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
 * #L%
 */

import java.util.Map;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.http.fast.HttpMetadata;
import org.rapidoid.http.fast.ParamHandler;
import org.rapidoid.lambda.F2;
import org.rapidoid.lambda.F3;
import org.rapidoid.lambda.Mapper;

@Authors("Nikolche Mihajlovski")
@Since("5.0.0")
public class HttpHandlers implements HttpMetadata {

	private static String param(Map<String, Object> req, final String paramName) {
		return (String) req.get(paramName);
	}

	public static ParamHandler parameterized(final String paramName, final Mapper<String, Object> handler) {
		return new ParamHandler() {
			@Override
			public Object handle(Map<String, Object> params) throws Exception {
				String param = param(params, paramName);
				return handler.map(param);
			}
		};
	}

	public static ParamHandler parameterized(final String paramName1, final String paramName2,
			final F2<String, String, Object> handler) {
		return new ParamHandler() {
			@Override
			public Object handle(Map<String, Object> params) throws Exception {
				String param1 = param(params, paramName1);
				String param2 = param(params, paramName2);
				return handler.execute(param1, param2);
			}
		};
	}

	public static ParamHandler parameterized(final String paramName1, final String paramName2, final String paramName3,
			final F3<String, String, String, Object> handler) {
		return new ParamHandler() {
			@Override
			public Object handle(Map<String, Object> params) throws Exception {
				String param1 = param(params, paramName1);
				String param2 = param(params, paramName2);
				String param3 = param(params, paramName3);
				return handler.execute(param1, param2, param3);
			}
		};
	}

}