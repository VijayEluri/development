/*
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: TW (Taiwan - Traditional Chinese)
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必填",
		remote: "請修正此欄位",
		email: "請輸入正確的電子信箱",
		url: "請輸入合法的URL",
		date: "請輸入合法的日期",
		dateISO: "請輸入合法的日期 (ISO).",
		number: "請輸入數字",
		digits: "請輸入整數",
		creditcard: "請輸入合法的信用卡號碼",
		equalTo: "請重複輸入一次",
		accept: "請輸入有效的後缀字串",
		maxlength: jQuery.validator.format("請輸入長度不大於{0} 的字串"),
		minlength: jQuery.validator.format("請輸入長度不小於 {0} 的字串"),
		rangelength: jQuery.validator.format("請輸入長度介於 {0} 和 {1} 之間的字串"),
		range: jQuery.validator.format("請輸入介於 {0} 和 {1} 之間的數值"),
		max: jQuery.validator.format("請輸入不大於 {0} 的數值"),
		min: jQuery.validator.format("請輸入不小於 {0} 的數值")
});