grails.mail.default.from = "4tablo <no-reply@4tablo.ir>"
grails {
    mail {
        ssl = "off"
        host = "mail.4tablo.ir"
        from = "no-reply@4tablo.ir"
        port = 25
        ssl = "off"
        username = "no-reply@4tablo.ir"
        password = 'agah!#($'
        props = [
                "mail.debug"             : "true",
                "mail.transport.protocol": "smtp",
                "mail.smtp.host"         : "mail.4tablo.ir",
                "mail.smtp.port"         : "25",
                "mail.smtp.auth"         : "true"
        ]

    }
}
