/**
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
package flowtrac.core.model.user;

import flowtrac.core.model.Item;

/**
 * The encapsulation of a user.
 * @author Markus Staeuble
 * 
 */
public interface User extends Item {
	/**
	 * @return Get the role of the user.
	 */
	Role getRole();

	/**
	 * @return The username (alias) of the user in the system. That name is the
	 *         unique identifier in the system.
	 */
	String getUsername();

	/**
	 * @return The name of the user.
	 */
	String getName();

	/**
	 * @return The email of the user.
	 */
	String getEmail();

	/**
	 * @return The password of the user.
	 */
	String getPassword();
}