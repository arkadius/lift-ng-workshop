/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package code.comet

import code.domain.{NumbersActor, Numbers}
import net.liftmodules.ng.{BindingToClient, SimpleNgModelBinder}
import net.liftweb.common.SimpleActor
import net.liftweb.http.CometListener

class NumbersBinder
  extends SimpleNgModelBinder[Numbers]("numbers", Numbers.zero)
  with BindingToClient
  with CometListener {

  override protected def registerWith: SimpleActor[Any] = NumbersActor
}
