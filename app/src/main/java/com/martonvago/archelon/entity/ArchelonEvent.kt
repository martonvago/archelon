package com.martonvago.archelon.entity

/**
 * An interface implemented by all morning survey events, where common behaviour and properties
 * can be specified. Each event type has its own entity and database table because - once fully
 * implemented - they will each have distinct sets of properties.
 */
interface ArchelonEvent {
    var surveyId: Long?
}