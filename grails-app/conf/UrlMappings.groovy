class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?/$name?"{
            constraints {
                // apply constraints here
            }
        }
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }


        "/"( controller: "/dashboard")
        "500"(view:'/error')
        "/index.gsp"( controller: "/dashboard")
	}
}
