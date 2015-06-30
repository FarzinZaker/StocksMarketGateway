class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }


        "/"( controller: "/landing")
        "500"(view:'/error')
        "/index.gsp"( controller: "/landing")
	}
}
