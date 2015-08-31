import org.scribe.builder.api.FacebookApi
import org.scribe.builder.api.GoogleApi
import org.scribe.builder.api.LinkedInApi
import org.scribe.builder.api.TwitterApi
import org.scribe.builder.api.YahooApi

oauth {
    providers {
        google {
            api = GoogleApi
            key = 'www.zanbil.ir'
            secret = 'ovX8LLv2vv9_3ZzfX-iXrF0s'
            scope = 'https://www.googleapis.com/auth/userinfo.profile'
//            api = org.scribe.builder.api.GoogleApi
//            key = 'oauth_google_key'
//            secret = 'oauth_google_secret'
            successUri = '/oauth/google/success'
            failureUri = '/oauth/google/error'
            callback = "http://www.4tablo.ir/oauth/google/callback"
            scope = 'https://www.googleapis.com/auth/userinfo.email'
        }
        yahoo {
            api = YahooApi
            key = 'dj0yJmk9cnhnQmFZclFtYjlwJmQ9WVdrOVdqTXhjWGxvTjJjbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD05Yw--'
            secret = '7dfc0469b2cd07fb78e42935c52025cc693015f6'
//            api = org.scribe.builder.api.YahooApi
//            key = 'oauth_yahoo_key'
//            secret = 'oauth_yahoo_secret'
            successUri = '/oauth/yahoo/success'
            failureUri = '/oauth/yahoo/error'
            callback = "http://www.4tablo.ir/oauth/yahoo/callback"
        }
        linkedin {
            api = LinkedInApi
            key = '773fej83o1cc0v'
            secret = 'OCXXLjhrKgtgV10y'
        }
        twitter {
            api = TwitterApi
            key = 'ru4m5xumFIUjfotNl2GfWVngq'
            secret = 'e5fcrpZxQUJTuvzjPLxbPKAkw2YezVmOQXkOoCb3Aqh6F44IxW'
        }
        facebook {
            api = FacebookApi
            key = '169034966762439'
            secret = 'b79f5323ae6f16b1c65662b2d4472021'
        }
    }
    debug = true
}

grails.plugin.invitation.facebook.enabled = true
grails.plugin.invitation.facebook.key = '456233177811011'
grails.plugin.invitation.facebook.secret = '91afef04ac96dc5c5413ac8f21086352'

grails.plugin.invitation.google.enabled = true
grails.plugin.invitation.google.key = 'www.zanbil.ir'
grails.plugin.invitation.google.secret = 'ovX8LLv2vv9_3ZzfX-iXrF0s'

grails.plugin.invitation.yahoo.enabled = true
grails.plugin.invitation.yahoo.key = 'dj0yJmk9WGZtVlJhS0xVem8wJmQ9WVdrOWFHUnZlbEYzTm5FbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD1kYw--'
grails.plugin.invitation.yahoo.secret = '0df982690055e700631968fc65b8d01d67ee3f6b'

grails.plugin.invitation.twitter.enabled = true
grails.plugin.invitation.twitter.key = 'TvO3rfrjDcUvsOSqmJafg'
grails.plugin.invitation.twitter.secret = 'GtPl4V1tKBnVLgy1ZnGERkcUnPIJExIpp2OCtZ98U'

grails.plugin.invitation.linkedin.enabled = true
grails.plugin.invitation.linkedin.key = 'djx324dkxfkz'
grails.plugin.invitation.linkedin.secret = 'zJRKXHw2lGn3zcEC'

grails.plugin.invitation.windowslive.enabled = true
grails.plugin.invitation.windowslive.key = '000000004C0FB2E9'
grails.plugin.invitation.windowslive.secret = 'zGCQO8mV4G7JSZS16RJu3fxcDeoqCVlt'