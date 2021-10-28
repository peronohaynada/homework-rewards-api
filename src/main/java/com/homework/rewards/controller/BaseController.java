package com.homework.rewards.controller;

import java.time.Instant;

public abstract class BaseController {

	/**
	 * Checks if the date is not in the future. Here we assume the date is converted to the server time zone.
	 * Otherwise this method needs to be revisited.
	 * @param date
	 * @return
	 */
	protected boolean isDateValid(final Long date) {
		Instant now = Instant.now();
		Long nowInMillis = now.toEpochMilli();
		return date != null && date < nowInMillis;
	}
}
