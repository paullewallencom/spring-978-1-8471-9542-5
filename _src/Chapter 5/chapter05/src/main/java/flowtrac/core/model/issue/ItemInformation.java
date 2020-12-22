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
package flowtrac.core.model.issue;

import java.util.Date;

import flowtrac.core.model.user.User;

/**
 * Metainformation about a item.
 * @author Markus Staeuble
 *
 */
public interface ItemInformation {
	
	/**
	 * @return Get the date of the creation of the item.
	 */
	Date getCreationDate();
	
	void setCreationDate(final Date creationDate);
	
	/**
	 * 
	 * @return The the date of the last modification of the item.
	 */
	Date getLastModified();
	
	void setLastModified(final Date lastModified);

	/**
	 * @return The user who has created the item.
	 */
	User getCreatedBy();
	
	void setCreatedBy(final User createdBy);

	/**	
	 * @return The user who has modified the item.
	 */
	User getLastModifiedBy();
	
	void setLastModifiedBy(final User lastModfiedBy);
}
