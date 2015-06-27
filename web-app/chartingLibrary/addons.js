var Global, AssetOptions, AssetType, Crud, Chart;
if (function (n, t) {
        function ti(n, r, u) {
            if (u === t && n.nodeType === 1) {
                var f = "data-" + r.replace(ni, "-$1").toLowerCase();
                if (u = n.getAttribute(f), typeof u == "string") {
                    try {
                        u = u === "true" ? !0 : u === "false" ? !1 : u === "null" ? null : i.isNaN(u) ? gt.test(u) ? i.parseJSON(u) : u : parseFloat(u)
                    } catch (e) {
                    }
                    i.data(n, r, u)
                } else u = t
            }
            return u
        }

        function ft(n) {
            for (var t in n)if (t !== "toJSON")return !1;
            return !0
        }

        function ii(n, r, u) {
            var f = r + "defer", e = r + "queue", o = r + "mark", s = i.data(n, f, t, !0);
            !s || u !== "queue" && i.data(n, e, t, !0) || u !== "mark" && i.data(n, o, t, !0) || setTimeout(function () {
                i.data(n, e, t, !0) || i.data(n, o, t, !0) || (i.removeData(n, f, !0), s.resolve())
            }, 0)
        }

        function o() {
            return !1
        }

        function b() {
            return !0
        }

        function ei(n, r, u) {
            var f = i.extend({}, u[0]);
            f.type = n;
            f.originalEvent = {};
            f.liveFired = t;
            i.event.handle.call(r, f);
            f.isDefaultPrevented() && u[0].preventDefault()
        }

        function eu(n) {
            var w, l, e, t, r, s, u, f, c, h, a, v, y = [], b = [], p = i._data(this, "events"), o;
            if (n.liveFired !== this && p && p.live && !n.target.disabled && (!n.button || n.type !== "click")) {
                for (n.namespace && (a = new RegExp("(^|\\.)" + n.namespace.split(".").join("\\.(?:.*\\.)?") + "(\\.|$)")), n.liveFired = this, o = p.live.slice(0), u = 0; u < o.length; u++)r = o[u], r.origType.replace(ot, "") === n.type ? b.push(r.selector) : o.splice(u--, 1);
                for (t = i(n.target).closest(b, n.currentTarget), f = 0, c = t.length; f < c; f++)for (h = t[f], u = 0; u < o.length; u++)r = o[u], h.selector !== r.selector || a && !a.test(r.namespace) || h.elem.disabled || (s = h.elem, e = null, (r.preType === "mouseenter" || r.preType === "mouseleave") && (n.type = r.preType, e = i(n.relatedTarget).closest(r.selector)[0], e && i.contains(s, e) && (e = s)), e && e === s || y.push({
                    elem: s,
                    handleObj: r,
                    level: h.level
                }));
                for (f = 0, c = y.length; f < c; f++) {
                    if (t = y[f], l && t.level > l)break;
                    if (n.currentTarget = t.elem, n.data = t.handleObj.data, n.handleObj = t.handleObj, v = t.handleObj.origHandler.apply(t.elem, arguments), (v === !1 || n.isPropagationStopped()) && (l = t.level, v === !1 && (w = !1), n.isImmediatePropagationStopped()))break
                }
                return w
            }
        }

        function d(n, t) {
            return (n && n !== "*" ? n + "." : "") + t.replace(iu, "`").replace(ru, "&")
        }

        function si(n) {
            return !n || !n.parentNode || n.parentNode.nodeType === 11
        }

        function hi(n, t, r) {
            if (t = t || 0, i.isFunction(t))return i.grep(n, function (n, i) {
                var u = !!t.call(n, i, n);
                return u === r
            });
            if (t.nodeType)return i.grep(n, function (n) {
                return n === t === r
            });
            if (typeof t == "string") {
                var u = i.grep(n, function (n) {
                    return n.nodeType === 1
                });
                if (cu.test(t))return i.filter(t, u, !r);
                t = i.filter(t, u)
            }
            return i.grep(n, function (n) {
                return i.inArray(n, t) >= 0 === r
            })
        }

        function ku(n) {
            return i.nodeName(n, "table") ? n.getElementsByTagName("tbody")[0] || n.appendChild(n.ownerDocument.createElement("tbody")) : n
        }

        function yi(n, t) {
            var r, u, f, h;
            if (t.nodeType === 1 && i.hasData(n)) {
                var s = i.expando, e = i.data(n), o = i.data(t, e);
                if ((e = e[s]) && (r = e.events, o = o[s] = i.extend({}, e), r)) {
                    delete o.handle;
                    o.events = {};
                    for (u in r)for (f = 0, h = r[u].length; f < h; f++)i.event.add(t, u + (r[u][f].namespace ? "." : "") + r[u][f].namespace, r[u][f], r[u][f].data)
                }
            }
        }

        function pi(n, t) {
            var r;
            t.nodeType === 1 && (t.clearAttributes && t.clearAttributes(), t.mergeAttributes && t.mergeAttributes(n), r = t.nodeName.toLowerCase(), r === "object" ? t.outerHTML = n.outerHTML : r === "input" && (n.type === "checkbox" || n.type === "radio") ? (n.checked && (t.defaultChecked = t.checked = n.checked), t.value !== n.value && (t.value = n.value)) : r === "option" ? t.selected = n.defaultSelected : (r === "input" || r === "textarea") && (t.defaultValue = n.defaultValue), t.removeAttribute(i.expando))
        }

        function g(n) {
            return "getElementsByTagName"in n ? n.getElementsByTagName("*") : "querySelectorAll"in n ? n.querySelectorAll("*") : []
        }

        function wi(n) {
            (n.type === "checkbox" || n.type === "radio") && (n.defaultChecked = n.checked)
        }

        function bi(n) {
            i.nodeName(n, "input") ? wi(n) : "getElementsByTagName"in n && i.grep(n.getElementsByTagName("input"), wi)
        }

        function du(n, t) {
            t.src ? i.ajax({
                url: t.src,
                async: !1,
                dataType: "script"
            }) : i.globalEval((t.text || t.textContent || t.innerHTML || "").replace(bu, "/*$0*/"));
            t.parentNode && t.parentNode.removeChild(t)
        }

        function nr(n, t, r) {
            var u = t === "width" ? n.offsetWidth : n.offsetHeight, f = t === "width" ? ff : ef;
            return u > 0 ? (r !== "border" && i.each(f, function () {
                r || (u -= parseFloat(i.css(n, "padding" + this)) || 0);
                r === "margin" ? u += parseFloat(i.css(n, r + this)) || 0 : u -= parseFloat(i.css(n, "border" + this + "Width")) || 0
            }), u + "px") : (u = c(n, t, t), (u < 0 || u == null) && (u = n.style[t] || 0), u = parseFloat(u) || 0, r && i.each(f, function () {
                u += parseFloat(i.css(n, "padding" + this)) || 0;
                r !== "padding" && (u += parseFloat(i.css(n, "border" + this + "Width")) || 0);
                r === "margin" && (u += parseFloat(i.css(n, r + this)) || 0)
            }), u + "px")
        }

        function sr(n) {
            return function (t, r) {
                if (typeof t != "string" && (r = t, t = "*"), i.isFunction(r))for (var o = t.toLowerCase().split(rr), f = 0, h = o.length, u, s, e; f < h; f++)u = o[f], e = /^\+/.test(u), e && (u = u.substr(1) || "*"), s = n[u] = n[u] || [], s[e ? "unshift" : "push"](r)
            }
        }

        function nt(n, i, r, u, f, e) {
            f = f || i.dataTypes[0];
            e = e || {};
            e[f] = !0;
            for (var s = n[f], h = 0, l = s ? s.length : 0, c = n === yt, o; h < l && (c || !o); h++)o = s[h](i, r, u), typeof o == "string" && (!c || e[o] ? o = t : (i.dataTypes.unshift(o), o = nt(n, i, r, u, o, e)));
            return !c && o || e["*"] || (o = nt(n, i, r, u, "*", e)), o
        }

        function hr(n, r) {
            var u, f, e = i.ajaxSettings.flatOptions || {};
            for (u in r)r[u] !== t && ((e[u] ? n : f || (f = {}))[u] = r[u]);
            f && i.extend(!0, n, f)
        }

        function pt(n, t, r, u) {
            if (i.isArray(t))i.each(t, function (t, f) {
                r || sf.test(n) ? u(n, f) : pt(n + "[" + (typeof f == "object" || i.isArray(f) ? t : "") + "]", f, r, u)
            }); else if (r || t == null || typeof t != "object")u(n, t); else for (var f in t)pt(n + "[" + f + "]", t[f], r, u)
        }

        function bf(n, i, r) {
            var s = n.contents, f = n.dataTypes, c = n.responseFields, o, u, e, h;
            for (u in c)u in r && (i[c[u]] = r[u]);
            while (f[0] === "*")f.shift(), o === t && (o = n.mimeType || i.getResponseHeader("content-type"));
            if (o)for (u in s)if (s[u] && s[u].test(o)) {
                f.unshift(u);
                break
            }
            if (f[0]in r)e = f[0]; else {
                for (u in r) {
                    if (!f[0] || n.converters[u + " " + f[0]]) {
                        e = u;
                        break
                    }
                    h || (h = u)
                }
                e = e || h
            }
            if (e)return e !== f[0] && f.unshift(e), r[e]
        }

        function kf(n, r) {
            n.dataFilter && (r = n.dataFilter(r, n.dataType));
            for (var v = n.dataTypes, s = {}, l, p = v.length, a, u = v[0], h, y, f, e, o, c = 1; c < p; c++) {
                if (c === 1)for (l in n.converters)typeof l == "string" && (s[l.toLowerCase()] = n.converters[l]);
                if (h = u, u = v[c], u === "*")u = h; else if (h !== "*" && h !== u) {
                    if (y = h + " " + u, f = s[y] || s["* " + u], !f) {
                        o = t;
                        for (e in s)if (a = e.split(" "), (a[0] === h || a[0] === "*") && (o = s[a[1] + " " + u], o)) {
                            e = s[e];
                            e === !0 ? f = o : o === !0 && (f = e);
                            break
                        }
                    }
                    f || o || i.error("No conversion from " + y.replace(" ", " to "));
                    f !== !0 && (r = f ? f(r) : o(e(r)))
                }
            }
            return r
        }

        function ar() {
            try {
                return new n.XMLHttpRequest
            } catch (t) {
            }
        }

        function df() {
            try {
                return new n.ActiveXObject("Microsoft.XMLHTTP")
            } catch (t) {
            }
        }

        function yr() {
            return setTimeout(te, 0), rt = i.now()
        }

        function te() {
            rt = t
        }

        function v(n, t) {
            var r = {};
            return i.each(vr.concat.apply([], vr.slice(0, t)), function () {
                r[this] = n
            }), r
        }

        function pr(n) {
            if (!wt[n]) {
                var e = r.body, t = i("<" + n + ">").appendTo(e), u = t.css("display");
                t.remove();
                (u === "none" || u === "") && (f || (f = r.createElement("iframe"), f.frameBorder = f.width = f.height = 0), e.appendChild(f), a && f.createElement || (a = (f.contentWindow || f.contentDocument).document, a.write((r.compatMode === "CSS1Compat" ? "<!doctype html>" : "") + "<html><body>"), a.close()), t = a.createElement(n), a.body.appendChild(t), u = i.css(t, "display"), e.removeChild(f));
                wt[n] = u
            }
            return wt[n]
        }

        function kt(n) {
            return i.isWindow(n) ? n : n.nodeType === 9 ? n.defaultView || n.parentWindow : !1
        }

        var r = n.document, br = n.navigator, kr = n.location, i = function () {
            function b() {
                if (!i.isReady) {
                    try {
                        r.documentElement.doScroll("left")
                    } catch (n) {
                        setTimeout(b, 1);
                        return
                    }
                    i.ready()
                }
            }

            var i = function (n, t) {
                return new i.fn.init(n, t, c)
            }, k = n.jQuery, d = n.$, c, g = /^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/, l = /\S/, a = /^\s+/, v = /\s+$/, nt = /\d/, tt = /^<(\w+)\s*\/?>(?:<\/\1>)?$/, it = /^[\],:{}\s]*$/, rt = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, ut = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ft = /(?:^|:|,)(?:\s*\[)+/g, et = /(webkit)[ \/]([\w.]+)/, ot = /(opera)(?:.*version)?[ \/]([\w.]+)/, st = /(msie) ([\w.]+)/, ht = /(mozilla)(?:.*? rv:([\w.]+))?/, ct = /-([a-z]|[0-9])/ig, lt = /^-ms-/, at = function (n, t) {
                return (t + "").toUpperCase()
            }, vt = br.userAgent, e, o, u, yt = Object.prototype.toString, s = Object.prototype.hasOwnProperty, h = Array.prototype.push, f = Array.prototype.slice, y = String.prototype.trim, p = Array.prototype.indexOf, w = {};
            return i.fn = i.prototype = {
                constructor: i, init: function (n, u, f) {
                    var o, s, e, h;
                    if (!n)return this;
                    if (n.nodeType)return this.context = this[0] = n, this.length = 1, this;
                    if (n === "body" && !u && r.body)return this.context = r, this[0] = r.body, this.selector = n, this.length = 1, this;
                    if (typeof n == "string") {
                        if (o = n.charAt(0) === "<" && n.charAt(n.length - 1) === ">" && n.length >= 3 ? [null, n, null] : g.exec(n), o && (o[1] || !u)) {
                            if (o[1])return u = u instanceof i ? u[0] : u, h = u ? u.ownerDocument || u : r, e = tt.exec(n), e ? i.isPlainObject(u) ? (n = [r.createElement(e[1])], i.fn.attr.call(n, u, !0)) : n = [h.createElement(e[1])] : (e = i.buildFragment([o[1]], [h]), n = (e.cacheable ? i.clone(e.fragment) : e.fragment).childNodes), i.merge(this, n);
                            if (s = r.getElementById(o[2]), s && s.parentNode) {
                                if (s.id !== o[2])return f.find(n);
                                this.length = 1;
                                this[0] = s
                            }
                            return this.context = r, this.selector = n, this
                        }
                        return !u || u.jquery ? (u || f).find(n) : this.constructor(u).find(n)
                    }
                    return i.isFunction(n) ? f.ready(n) : (n.selector !== t && (this.selector = n.selector, this.context = n.context), i.makeArray(n, this))
                }, selector: "", jquery: "1.6.4", length: 0, size: function () {
                    return this.length
                }, toArray: function () {
                    return f.call(this, 0)
                }, get: function (n) {
                    return n == null ? this.toArray() : n < 0 ? this[this.length + n] : this[n]
                }, pushStack: function (n, t, r) {
                    var u = this.constructor();
                    return i.isArray(n) ? h.apply(u, n) : i.merge(u, n), u.prevObject = this, u.context = this.context, t === "find" ? u.selector = this.selector + (this.selector ? " " : "") + r : t && (u.selector = this.selector + "." + t + "(" + r + ")"), u
                }, each: function (n, t) {
                    return i.each(this, n, t)
                }, ready: function (n) {
                    return i.bindReady(), o.done(n), this
                }, eq: function (n) {
                    return n === -1 ? this.slice(n) : this.slice(n, +n + 1)
                }, first: function () {
                    return this.eq(0)
                }, last: function () {
                    return this.eq(-1)
                }, slice: function () {
                    return this.pushStack(f.apply(this, arguments), "slice", f.call(arguments).join(","))
                }, map: function (n) {
                    return this.pushStack(i.map(this, function (t, i) {
                        return n.call(t, i, t)
                    }))
                }, end: function () {
                    return this.prevObject || this.constructor(null)
                }, push: h, sort: [].sort, splice: [].splice
            }, i.fn.init.prototype = i.fn, i.extend = i.fn.extend = function () {
                var o, e, u, r, s, h, n = arguments[0] || {}, f = 1, l = arguments.length, c = !1;
                for (typeof n == "boolean" && (c = n, n = arguments[1] || {}, f = 2), typeof n == "object" || i.isFunction(n) || (n = {}), l === f && (n = this, --f); f < l; f++)if ((o = arguments[f]) != null)for (e in o)(u = n[e], r = o[e], n !== r) && (c && r && (i.isPlainObject(r) || (s = i.isArray(r))) ? (s ? (s = !1, h = u && i.isArray(u) ? u : []) : h = u && i.isPlainObject(u) ? u : {}, n[e] = i.extend(c, h, r)) : r !== t && (n[e] = r));
                return n
            }, i.extend({
                noConflict: function (t) {
                    return n.$ === i && (n.$ = d), t && n.jQuery === i && (n.jQuery = k), i
                }, isReady: !1, readyWait: 1, holdReady: function (n) {
                    n ? i.readyWait++ : i.ready(!0)
                }, ready: function (n) {
                    if (n === !0 && !--i.readyWait || n !== !0 && !i.isReady) {
                        if (!r.body)return setTimeout(i.ready, 1);
                        if (i.isReady = !0, n !== !0 && --i.readyWait > 0)return;
                        o.resolveWith(r, [i]);
                        i.fn.trigger && i(r).trigger("ready").unbind("ready")
                    }
                }, bindReady: function () {
                    if (!o) {
                        if (o = i._Deferred(), r.readyState === "complete")return setTimeout(i.ready, 1);
                        if (r.addEventListener)r.addEventListener("DOMContentLoaded", u, !1), n.addEventListener("load", i.ready, !1); else if (r.attachEvent) {
                            r.attachEvent("onreadystatechange", u);
                            n.attachEvent("onload", i.ready);
                            var t = !1;
                            try {
                                t = n.frameElement == null
                            } catch (f) {
                            }
                            r.documentElement.doScroll && t && b()
                        }
                    }
                }, isFunction: function (n) {
                    return i.type(n) === "function"
                }, isArray: Array.isArray || function (n) {
                    return i.type(n) === "array"
                }, isWindow: function (n) {
                    return n && typeof n == "object" && "setInterval"in n
                }, isNaN: function (n) {
                    return n == null || !nt.test(n) || isNaN(n)
                }, type: function (n) {
                    return n == null ? String(n) : w[yt.call(n)] || "object"
                }, isPlainObject: function (n) {
                    if (!n || i.type(n) !== "object" || n.nodeType || i.isWindow(n))return !1;
                    try {
                        if (n.constructor && !s.call(n, "constructor") && !s.call(n.constructor.prototype, "isPrototypeOf"))return !1
                    } catch (u) {
                        return !1
                    }
                    var r;
                    for (r in n);
                    return r === t || s.call(n, r)
                }, isEmptyObject: function (n) {
                    for (var t in n)return !1;
                    return !0
                }, error: function (n) {
                    throw n;
                }, parseJSON: function (t) {
                    if (typeof t != "string" || !t)return null;
                    if (t = i.trim(t), n.JSON && n.JSON.parse)return n.JSON.parse(t);
                    if (it.test(t.replace(rt, "@").replace(ut, "]").replace(ft, "")))return new Function("return " + t)();
                    i.error("Invalid JSON: " + t)
                }, parseXML: function (r) {
                    var u, f;
                    try {
                        n.DOMParser ? (f = new DOMParser, u = f.parseFromString(r, "text/xml")) : (u = new ActiveXObject("Microsoft.XMLDOM"), u.async = "false", u.loadXML(r))
                    } catch (e) {
                        u = t
                    }
                    return u && u.documentElement && !u.getElementsByTagName("parsererror").length || i.error("Invalid XML: " + r), u
                }, noop: function () {
                }, globalEval: function (t) {
                    t && l.test(t) && (n.execScript || function (t) {
                        n.eval.call(n, t)
                    })(t)
                }, camelCase: function (n) {
                    return n.replace(lt, "ms-").replace(ct, at)
                }, nodeName: function (n, t) {
                    return n.nodeName && n.nodeName.toUpperCase() === t.toUpperCase()
                }, each: function (n, r, u) {
                    var f, e = 0, o = n.length, s = o === t || i.isFunction(n);
                    if (u) {
                        if (s) {
                            for (f in n)if (r.apply(n[f], u) === !1)break
                        } else for (; e < o;)if (r.apply(n[e++], u) === !1)break
                    } else if (s) {
                        for (f in n)if (r.call(n[f], f, n[f]) === !1)break
                    } else for (; e < o;)if (r.call(n[e], e, n[e++]) === !1)break;
                    return n
                }, trim: y ? function (n) {
                    return n == null ? "" : y.call(n)
                } : function (n) {
                    return n == null ? "" : n.toString().replace(a, "").replace(v, "")
                }, makeArray: function (n, t) {
                    var u = t || [], r;
                    return n != null && (r = i.type(n), n.length == null || r === "string" || r === "function" || r === "regexp" || i.isWindow(n) ? h.call(u, n) : i.merge(u, n)), u
                }, inArray: function (n, t) {
                    if (!t)return -1;
                    if (p)return p.call(t, n);
                    for (var i = 0, r = t.length; i < r; i++)if (t[i] === n)return i;
                    return -1
                }, merge: function (n, i) {
                    var u = n.length, r = 0, f;
                    if (typeof i.length == "number")for (f = i.length; r < f; r++)n[u++] = i[r]; else while (i[r] !== t)n[u++] = i[r++];
                    return n.length = u, n
                }, grep: function (n, t, i) {
                    var u = [], f, r, e;
                    for (i = !!i, r = 0, e = n.length; r < e; r++)f = !!t(n[r], r), i !== f && u.push(n[r]);
                    return u
                }, map: function (n, r, u) {
                    var f, h, e = [], s = 0, o = n.length, c = n instanceof i || o !== t && typeof o == "number" && (o > 0 && n[0] && n[o - 1] || o === 0 || i.isArray(n));
                    if (c)for (; s < o; s++)f = r(n[s], s, u), f != null && (e[e.length] = f); else for (h in n)f = r(n[h], h, u), f != null && (e[e.length] = f);
                    return e.concat.apply([], e)
                }, guid: 1, proxy: function (n, r) {
                    var e, o, u;
                    return (typeof r == "string" && (e = n[r], r = n, n = e), !i.isFunction(n)) ? t : (o = f.call(arguments, 2), u = function () {
                        return n.apply(r, o.concat(f.call(arguments)))
                    }, u.guid = n.guid = n.guid || u.guid || i.guid++, u)
                }, access: function (n, r, u, f, e, o) {
                    var c = n.length, h, s;
                    if (typeof r == "object") {
                        for (h in r)i.access(n, h, r[h], f, e, u);
                        return n
                    }
                    if (u !== t) {
                        for (f = !o && f && i.isFunction(u), s = 0; s < c; s++)e(n[s], r, f ? u.call(n[s], s, e(n[s], r)) : u, o);
                        return n
                    }
                    return c ? e(n[0], r) : t
                }, now: function () {
                    return (new Date).getTime()
                }, uaMatch: function (n) {
                    n = n.toLowerCase();
                    var t = et.exec(n) || ot.exec(n) || st.exec(n) || n.indexOf("compatible") < 0 && ht.exec(n) || [];
                    return {browser: t[1] || "", version: t[2] || "0"}
                }, sub: function () {
                    function n(t, i) {
                        return new n.fn.init(t, i)
                    }

                    i.extend(!0, n, this);
                    n.superclass = this;
                    n.fn = n.prototype = this();
                    n.fn.constructor = n;
                    n.sub = this.sub;
                    n.fn.init = function (r, u) {
                        return u && u instanceof i && !(u instanceof n) && (u = n(u)), i.fn.init.call(this, r, u, t)
                    };
                    n.fn.init.prototype = n.fn;
                    var t = n(r);
                    return n
                }, browser: {}
            }), i.each("Boolean Number String Function Array Date RegExp Object".split(" "), function (n, t) {
                w["[object " + t + "]"] = t.toLowerCase()
            }), e = i.uaMatch(vt), e.browser && (i.browser[e.browser] = !0, i.browser.version = e.version), i.browser.webkit && (i.browser.safari = !0), l.test(" ") && (a = /^[\s\xA0]+/, v = /[\s\xA0]+$/), c = i(r), r.addEventListener ? u = function () {
                r.removeEventListener("DOMContentLoaded", u, !1);
                i.ready()
            } : r.attachEvent && (u = function () {
                r.readyState === "complete" && (r.detachEvent("onreadystatechange", u), i.ready())
            }), i
        }(), ut = "done fail isResolved isRejected promise then always pipe".split(" "), dt = [].slice, gt, ni, ht, ct, y, lt, p, k, cr, w, tt, lr, l, wr, bt;
        i.extend({
            _Deferred: function () {
                var r = [], n, u, f, t = {
                    done: function () {
                        if (!f) {
                            var h = arguments, u, c, e, s, o;
                            for (n && (o = n, n = 0), u = 0, c = h.length; u < c; u++)e = h[u], s = i.type(e), s === "array" ? t.done.apply(t, e) : s === "function" && r.push(e);
                            o && t.resolveWith(o[0], o[1])
                        }
                        return this
                    }, resolveWith: function (t, i) {
                        if (!f && !n && !u) {
                            i = i || [];
                            u = 1;
                            try {
                                while (r[0])r.shift().apply(t, i)
                            } finally {
                                n = [t, i];
                                u = 0
                            }
                        }
                        return this
                    }, resolve: function () {
                        return t.resolveWith(this, arguments), this
                    }, isResolved: function () {
                        return !!(u || n)
                    }, cancel: function () {
                        return f = 1, r = [], this
                    }
                };
                return t
            }, Deferred: function (n) {
                var t = i._Deferred(), r = i._Deferred(), u;
                return i.extend(t, {
                    then: function (n, i) {
                        return t.done(n).fail(i), this
                    },
                    always: function () {
                        return t.done.apply(t, arguments).fail.apply(this, arguments)
                    },
                    fail: r.done,
                    rejectWith: r.resolveWith,
                    reject: r.resolve,
                    isRejected: r.isResolved,
                    pipe: function (n, r) {
                        return i.Deferred(function (u) {
                            i.each({done: [n, "resolve"], fail: [r, "reject"]}, function (n, r) {
                                var e = r[0], o = r[1], f;
                                i.isFunction(e) ? t[n](function () {
                                    f = e.apply(this, arguments);
                                    f && i.isFunction(f.promise) ? f.promise().then(u.resolve, u.reject) : u[o + "With"](this === t ? u : this, [f])
                                }) : t[n](u[o])
                            })
                        }).promise()
                    },
                    promise: function (n) {
                        if (n == null) {
                            if (u)return u;
                            u = n = {}
                        }
                        for (var i = ut.length; i--;)n[ut[i]] = t[ut[i]];
                        return n
                    }
                }), t.done(r.cancel).fail(t.cancel), delete t.cancel, n && n.call(t, t), t
            }, when: function (n) {
                function o(n) {
                    return function (i) {
                        r[n] = arguments.length > 1 ? dt.call(arguments, 0) : i;
                        --e || t.resolveWith(t, dt.call(r, 0))
                    }
                }

                var r = arguments, u = 0, f = r.length, e = f, t = f <= 1 && n && i.isFunction(n.promise) ? n : i.Deferred();
                if (f > 1) {
                    for (; u < f; u++)r[u] && i.isFunction(r[u].promise) ? r[u].promise().then(o(u), t.reject) : --e;
                    e || t.resolveWith(t, r)
                } else t !== n && t.resolveWith(t, f ? [n] : []);
                return t.promise()
            }
        });
        i.support = function () {
            var n = r.createElement("div"), d = r.documentElement, k, e, a, v, u, o, t, y, c, p, f, w, l, b, s, h;
            if (n.setAttribute("className", "t"), n.innerHTML = "   <link/><table><\/table><a href='/a' style='top:1px;float:left;opacity:.55;'>a<\/a><input type='checkbox'/>", k = n.getElementsByTagName("*"), e = n.getElementsByTagName("a")[0], !k || !k.length || !e)return {};
            a = r.createElement("select");
            v = a.appendChild(r.createElement("option"));
            u = n.getElementsByTagName("input")[0];
            t = {
                leadingWhitespace: n.firstChild.nodeType === 3,
                tbody: !n.getElementsByTagName("tbody").length,
                htmlSerialize: !!n.getElementsByTagName("link").length,
                style: /top/.test(e.getAttribute("style")),
                hrefNormalized: e.getAttribute("href") === "/a",
                opacity: /^0.55$/.test(e.style.opacity),
                cssFloat: !!e.style.cssFloat,
                checkOn: u.value === "on",
                optSelected: v.selected,
                getSetAttribute: n.className !== "t",
                submitBubbles: !0,
                changeBubbles: !0,
                focusinBubbles: !1,
                deleteExpando: !0,
                noCloneEvent: !0,
                inlineBlockNeedsLayout: !1,
                shrinkWrapBlocks: !1,
                reliableMarginRight: !0
            };
            u.checked = !0;
            t.noCloneChecked = u.cloneNode(!0).checked;
            a.disabled = !0;
            t.optDisabled = !v.disabled;
            try {
                delete n.test
            } catch (g) {
                t.deleteExpando = !1
            }
            !n.addEventListener && n.attachEvent && n.fireEvent && (n.attachEvent("onclick", function () {
                t.noCloneEvent = !1
            }), n.cloneNode(!0).fireEvent("onclick"));
            u = r.createElement("input");
            u.value = "t";
            u.setAttribute("type", "radio");
            t.radioValue = u.value === "t";
            u.setAttribute("checked", "checked");
            n.appendChild(u);
            y = r.createDocumentFragment();
            y.appendChild(n.firstChild);
            t.checkClone = y.cloneNode(!0).cloneNode(!0).lastChild.checked;
            n.innerHTML = "";
            n.style.width = n.style.paddingLeft = "1px";
            c = r.getElementsByTagName("body")[0];
            f = r.createElement(c ? "div" : "body");
            w = {visibility: "hidden", width: 0, height: 0, border: 0, margin: 0, background: "none"};
            c && i.extend(w, {position: "absolute", left: "-1000px", top: "-1000px"});
            for (s in w)f.style[s] = w[s];
            if (f.appendChild(n), p = c || d, p.insertBefore(f, p.firstChild), t.appendChecked = u.checked, t.boxModel = n.offsetWidth === 2, "zoom"in n.style && (n.style.display = "inline", n.style.zoom = 1, t.inlineBlockNeedsLayout = n.offsetWidth === 2, n.style.display = "", n.innerHTML = "<div style='width:4px;'><\/div>", t.shrinkWrapBlocks = n.offsetWidth !== 2), n.innerHTML = "<table><tr><td style='padding:0;border:0;display:none'><\/td><td>t<\/td><\/tr><\/table>", l = n.getElementsByTagName("td"), h = l[0].offsetHeight === 0, l[0].style.display = "", l[1].style.display = "none", t.reliableHiddenOffsets = h && l[0].offsetHeight === 0, n.innerHTML = "", r.defaultView && r.defaultView.getComputedStyle && (o = r.createElement("div"), o.style.width = "0", o.style.marginRight = "0", n.appendChild(o), t.reliableMarginRight = (parseInt((r.defaultView.getComputedStyle(o, null) || {marginRight: 0}).marginRight, 10) || 0) === 0), f.innerHTML = "", p.removeChild(f), n.attachEvent)for (s in{
                submit: 1,
                change: 1,
                focusin: 1
            })b = "on" + s, h = b in n, h || (n.setAttribute(b, "return;"), h = typeof n[b] == "function"), t[s + "Bubbles"] = h;
            return f = y = a = v = c = o = n = u = null, t
        }();
        i.boxModel = i.support.boxModel;
        gt = /^(?:\{.*\}|\[.*\])$/;
        ni = /([A-Z])/g;
        i.extend({
            cache: {},
            uuid: 0,
            expando: "jQuery" + (i.fn.jquery + Math.random()).replace(/\D/g, ""),
            noData: {embed: !0, object: "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000", applet: !0},
            hasData: function (n) {
                return n = n.nodeType ? i.cache[n[i.expando]] : n[i.expando], !!n && !ft(n)
            },
            data: function (n, r, u, f) {
                if (i.acceptData(n)) {
                    var o, c, h = i.expando, a = typeof r == "string", l = n.nodeType, s = l ? i.cache : n, e = l ? n[i.expando] : n[i.expando] && i.expando;
                    if (e && (!f || !e || !s[e] || s[e][h]) || !a || u !== t)return (e || (l ? n[i.expando] = e = ++i.uuid : e = i.expando), s[e] || (s[e] = {}, l || (s[e].toJSON = i.noop)), (typeof r == "object" || typeof r == "function") && (f ? s[e][h] = i.extend(s[e][h], r) : s[e] = i.extend(s[e], r)), o = s[e], f && (o[h] || (o[h] = {}), o = o[h]), u !== t && (o[i.camelCase(r)] = u), r === "events" && !o[r]) ? o[h] && o[h].events : (a ? (c = o[r], c == null && (c = o[i.camelCase(r)])) : c = o, c)
                }
            },
            removeData: function (n, t, r) {
                var h;
                if (i.acceptData(n)) {
                    var e, o = i.expando, s = n.nodeType, u = s ? i.cache : n, f = s ? n[i.expando] : i.expando;
                    u[f] && (t && (e = r ? u[f][o] : u[f], e && (e[t] || (t = i.camelCase(t)), delete e[t], !ft(e))) || (!r || (delete u[f][o], ft(u[f]))) && (h = u[f][o], i.support.deleteExpando || !u.setInterval ? delete u[f] : u[f] = null, h ? (u[f] = {}, s || (u[f].toJSON = i.noop), u[f][o] = h) : s && (i.support.deleteExpando ? delete n[i.expando] : n.removeAttribute ? n.removeAttribute(i.expando) : n[i.expando] = null)))
                }
            },
            _data: function (n, t, r) {
                return i.data(n, t, r, !0)
            },
            acceptData: function (n) {
                if (n.nodeName) {
                    var t = i.noData[n.nodeName.toLowerCase()];
                    if (t)return !(t === !0 || n.getAttribute("classid") !== t)
                }
                return !0
            }
        });
        i.fn.extend({
            data: function (n, r) {
                var f = null, s, e, o, h, u;
                if (typeof n == "undefined") {
                    if (this.length && (f = i.data(this[0]), this[0].nodeType === 1))for (s = this[0].attributes, o = 0, h = s.length; o < h; o++)e = s[o].name, e.indexOf("data-") === 0 && (e = i.camelCase(e.substring(5)), ti(this[0], e, f[e]));
                    return f
                }
                return typeof n == "object" ? this.each(function () {
                    i.data(this, n)
                }) : (u = n.split("."), u[1] = u[1] ? "." + u[1] : "", r === t ? (f = this.triggerHandler("getData" + u[1] + "!", [u[0]]), f === t && this.length && (f = i.data(this[0], n), f = ti(this[0], n, f)), f === t && u[1] ? this.data(u[0]) : f) : this.each(function () {
                    var t = i(this), f = [u[0], r];
                    t.triggerHandler("setData" + u[1] + "!", f);
                    i.data(this, n, r);
                    t.triggerHandler("changeData" + u[1] + "!", f)
                }))
            }, removeData: function (n) {
                return this.each(function () {
                    i.removeData(this, n)
                })
            }
        });
        i.extend({
            _mark: function (n, r) {
                n && (r = (r || "fx") + "mark", i.data(n, r, (i.data(n, r, t, !0) || 0) + 1, !0))
            }, _unmark: function (n, r, u) {
                if (n !== !0 && (u = r, r = n, n = !1), r) {
                    u = u || "fx";
                    var f = u + "mark", e = n ? 0 : (i.data(r, f, t, !0) || 1) - 1;
                    e ? i.data(r, f, e, !0) : (i.removeData(r, f, !0), ii(r, u, "mark"))
                }
            }, queue: function (n, r, u) {
                if (n) {
                    r = (r || "fx") + "queue";
                    var f = i.data(n, r, t, !0);
                    return u && (!f || i.isArray(u) ? f = i.data(n, r, i.makeArray(u), !0) : f.push(u)), f || []
                }
            }, dequeue: function (n, t) {
                t = t || "fx";
                var r = i.queue(n, t), u = r.shift();
                u === "inprogress" && (u = r.shift());
                u && (t === "fx" && r.unshift("inprogress"), u.call(n, function () {
                    i.dequeue(n, t)
                }));
                r.length || (i.removeData(n, t + "queue", !0), ii(n, t, "queue"))
            }
        });
        i.fn.extend({
            queue: function (n, r) {
                return (typeof n != "string" && (r = n, n = "fx"), r === t) ? i.queue(this[0], n) : this.each(function () {
                    var t = i.queue(this, n, r);
                    n === "fx" && t[0] !== "inprogress" && i.dequeue(this, n)
                })
            }, dequeue: function (n) {
                return this.each(function () {
                    i.dequeue(this, n)
                })
            }, delay: function (n, t) {
                return n = i.fx ? i.fx.speeds[n] || n : n, t = t || "fx", this.queue(t, function () {
                    var r = this;
                    setTimeout(function () {
                        i.dequeue(r, t)
                    }, n)
                })
            }, clearQueue: function (n) {
                return this.queue(n || "fx", [])
            }, promise: function (n, r) {
                function c() {
                    --o || e.resolveWith(u, [u])
                }

                typeof n != "string" && (r = n, n = t);
                n = n || "fx";
                for (var e = i.Deferred(), u = this, f = u.length, o = 1, s = n + "defer", l = n + "queue", a = n + "mark", h; f--;)(h = i.data(u[f], s, t, !0) || (i.data(u[f], l, t, !0) || i.data(u[f], a, t, !0)) && i.data(u[f], s, i._Deferred(), !0)) && (o++, h.done(c));
                return c(), e.promise()
            }
        });
        var ri = /[\n\t\r]/g, et = /\s+/, dr = /\r/g, gr = /^(?:button|input)$/i, nu = /^(?:button|input|object|select|textarea)$/i, tu = /^a(?:rea)?$/i, ui = /^(?:autofocus|autoplay|async|checked|controls|defer|disabled|hidden|loop|multiple|open|readonly|required|scoped|selected)$/i, e, fi;
        i.fn.extend({
            attr: function (n, t) {
                return i.access(this, n, t, !0, i.attr)
            }, removeAttr: function (n) {
                return this.each(function () {
                    i.removeAttr(this, n)
                })
            }, prop: function (n, t) {
                return i.access(this, n, t, !0, i.prop)
            }, removeProp: function (n) {
                return n = i.propFix[n] || n, this.each(function () {
                    try {
                        this[n] = t;
                        delete this[n]
                    } catch (i) {
                    }
                })
            }, addClass: function (n) {
                var r, f, o, t, e, u, s;
                if (i.isFunction(n))return this.each(function (t) {
                    i(this).addClass(n.call(this, t, this.className))
                });
                if (n && typeof n == "string")for (r = n.split(et), f = 0, o = this.length; f < o; f++)if (t = this[f], t.nodeType === 1)if (t.className || r.length !== 1) {
                    for (e = " " + t.className + " ", u = 0, s = r.length; u < s; u++)~e.indexOf(" " + r[u] + " ") || (e += r[u] + " ");
                    t.className = i.trim(e)
                } else t.className = n;
                return this
            }, removeClass: function (n) {
                var o, u, s, r, f, e, h;
                if (i.isFunction(n))return this.each(function (t) {
                    i(this).removeClass(n.call(this, t, this.className))
                });
                if (n && typeof n == "string" || n === t)for (o = (n || "").split(et), u = 0, s = this.length; u < s; u++)if (r = this[u], r.nodeType === 1 && r.className)if (n) {
                    for (f = (" " + r.className + " ").replace(ri, " "), e = 0, h = o.length; e < h; e++)f = f.replace(" " + o[e] + " ", " ");
                    r.className = i.trim(f)
                } else r.className = "";
                return this
            }, toggleClass: function (n, t) {
                var r = typeof n, u = typeof t == "boolean";
                return i.isFunction(n) ? this.each(function (r) {
                    i(this).toggleClass(n.call(this, r, this.className, t), t)
                }) : this.each(function () {
                    if (r === "string")for (var f, s = 0, o = i(this), e = t, h = n.split(et); f = h[s++];)e = u ? e : !o.hasClass(f), o[e ? "addClass" : "removeClass"](f); else(r === "undefined" || r === "boolean") && (this.className && i._data(this, "__className__", this.className), this.className = this.className || n === !1 ? "" : i._data(this, "__className__") || "")
                })
            }, hasClass: function (n) {
                for (var r = " " + n + " ", t = 0, i = this.length; t < i; t++)if (this[t].nodeType === 1 && (" " + this[t].className + " ").replace(ri, " ").indexOf(r) > -1)return !0;
                return !1
            }, val: function (n) {
                var r, u, f = this[0], e;
                return arguments.length ? (e = i.isFunction(n), this.each(function (u) {
                    var o = i(this), f;
                    this.nodeType === 1 && (f = e ? n.call(this, u, o.val()) : n, f == null ? f = "" : typeof f == "number" ? f += "" : i.isArray(f) && (f = i.map(f, function (n) {
                        return n == null ? "" : n + ""
                    })), r = i.valHooks[this.nodeName.toLowerCase()] || i.valHooks[this.type], r && "set"in r && r.set(this, f, "value") !== t || (this.value = f))
                })) : f ? (r = i.valHooks[f.nodeName.toLowerCase()] || i.valHooks[f.type], r && "get"in r && (u = r.get(f, "value")) !== t) ? u : (u = f.value, typeof u == "string" ? u.replace(dr, "") : u == null ? "" : u) : t
            }
        });
        i.extend({
            valHooks: {
                option: {
                    get: function (n) {
                        var t = n.attributes.value;
                        return !t || t.specified ? n.value : n.text
                    }
                }, select: {
                    get: function (n) {
                        var o, r = n.selectedIndex, s = [], u = n.options, f = n.type === "select-one", e, h, t;
                        if (r < 0)return null;
                        for (e = f ? r : 0, h = f ? r + 1 : u.length; e < h; e++)if (t = u[e], t.selected && (i.support.optDisabled ? !t.disabled : t.getAttribute("disabled") === null) && (!t.parentNode.disabled || !i.nodeName(t.parentNode, "optgroup"))) {
                            if (o = i(t).val(), f)return o;
                            s.push(o)
                        }
                        return f && !s.length && u.length ? i(u[r]).val() : s
                    }, set: function (n, t) {
                        var r = i.makeArray(t);
                        return i(n).find("option").each(function () {
                            this.selected = i.inArray(i(this).val(), r) >= 0
                        }), r.length || (n.selectedIndex = -1), r
                    }
                }
            },
            attrFn: {val: !0, css: !0, html: !0, text: !0, data: !0, width: !0, height: !0, offset: !0},
            attrFix: {tabindex: "tabIndex"},
            attr: function (n, r, u, f) {
                var h = n.nodeType, s, o, c;
                return !n || h === 3 || h === 8 || h === 2 ? t : f && r in i.attrFn ? i(n)[r](u) : ("getAttribute"in n) ? (c = h !== 1 || !i.isXMLDoc(n), c && (r = i.attrFix[r] || r, o = i.attrHooks[r], o || (ui.test(r) ? o = fi : e && (o = e))), u !== t ? u === null ? (i.removeAttr(n, r), t) : o && "set"in o && c && (s = o.set(n, u, r)) !== t ? s : (n.setAttribute(r, "" + u), u) : o && "get"in o && c && (s = o.get(n, r)) !== null ? s : (s = n.getAttribute(r), s === null ? t : s)) : i.prop(n, r, u)
            },
            removeAttr: function (n, t) {
                var r;
                n.nodeType === 1 && (t = i.attrFix[t] || t, i.attr(n, t, ""), n.removeAttribute(t), ui.test(t) && (r = i.propFix[t] || t)in n && (n[r] = !1))
            },
            attrHooks: {
                type: {
                    set: function (n, t) {
                        if (gr.test(n.nodeName) && n.parentNode)i.error("type property can't be changed"); else if (!i.support.radioValue && t === "radio" && i.nodeName(n, "input")) {
                            var r = n.value;
                            return n.setAttribute("type", t), r && (n.value = r), t
                        }
                    }
                }, value: {
                    get: function (n, t) {
                        return e && i.nodeName(n, "button") ? e.get(n, t) : t in n ? n.value : null
                    }, set: function (n, t, r) {
                        if (e && i.nodeName(n, "button"))return e.set(n, t, r);
                        n.value = t
                    }
                }
            },
            propFix: {
                tabindex: "tabIndex",
                readonly: "readOnly",
                "for": "htmlFor",
                "class": "className",
                maxlength: "maxLength",
                cellspacing: "cellSpacing",
                cellpadding: "cellPadding",
                rowspan: "rowSpan",
                colspan: "colSpan",
                usemap: "useMap",
                frameborder: "frameBorder",
                contenteditable: "contentEditable"
            },
            prop: function (n, r, u) {
                var e = n.nodeType, o, f, s;
                return !n || e === 3 || e === 8 || e === 2 ? t : (s = e !== 1 || !i.isXMLDoc(n), s && (r = i.propFix[r] || r, f = i.propHooks[r]), u !== t ? f && "set"in f && (o = f.set(n, u, r)) !== t ? o : n[r] = u : f && "get"in f && (o = f.get(n, r)) !== null ? o : n[r])
            },
            propHooks: {
                tabIndex: {
                    get: function (n) {
                        var i = n.getAttributeNode("tabindex");
                        return i && i.specified ? parseInt(i.value, 10) : nu.test(n.nodeName) || tu.test(n.nodeName) && n.href ? 0 : t
                    }
                }
            }
        });
        i.attrHooks.tabIndex = i.propHooks.tabIndex;
        fi = {
            get: function (n, r) {
                var u;
                return i.prop(n, r) === !0 || (u = n.getAttributeNode(r)) && u.nodeValue !== !1 ? r.toLowerCase() : t
            }, set: function (n, t, r) {
                var u;
                return t === !1 ? i.removeAttr(n, r) : (u = i.propFix[r] || r, u in n && (n[u] = !0), n.setAttribute(r, r.toLowerCase())), r
            }
        };
        i.support.getSetAttribute || (e = i.valHooks.button = {
            get: function (n, i) {
                var r;
                return r = n.getAttributeNode(i), r && r.nodeValue !== "" ? r.nodeValue : t
            }, set: function (n, t, i) {
                var u = n.getAttributeNode(i);
                return u || (u = r.createAttribute(i), n.setAttributeNode(u)), u.nodeValue = t + ""
            }
        }, i.each(["width", "height"], function (n, t) {
            i.attrHooks[t] = i.extend(i.attrHooks[t], {
                set: function (n, i) {
                    if (i === "")return n.setAttribute(t, "auto"), i
                }
            })
        }));
        i.support.hrefNormalized || i.each(["href", "src", "width", "height"], function (n, r) {
            i.attrHooks[r] = i.extend(i.attrHooks[r], {
                get: function (n) {
                    var i = n.getAttribute(r, 2);
                    return i === null ? t : i
                }
            })
        });
        i.support.style || (i.attrHooks.style = {
            get: function (n) {
                return n.style.cssText.toLowerCase() || t
            }, set: function (n, t) {
                return n.style.cssText = "" + t
            }
        });
        i.support.optSelected || (i.propHooks.selected = i.extend(i.propHooks.selected, {
            get: function (n) {
                var t = n.parentNode;
                return t && (t.selectedIndex, t.parentNode && t.parentNode.selectedIndex), null
            }
        }));
        i.support.checkOn || i.each(["radio", "checkbox"], function () {
            i.valHooks[this] = {
                get: function (n) {
                    return n.getAttribute("value") === null ? "on" : n.value
                }
            }
        });
        i.each(["radio", "checkbox"], function () {
            i.valHooks[this] = i.extend(i.valHooks[this], {
                set: function (n, t) {
                    if (i.isArray(t))return n.checked = i.inArray(i(n).val(), t) >= 0
                }
            })
        });
        var ot = /\.(.*)$/, st = /^(?:textarea|input|select)$/i, iu = /\./g, ru = / /g, uu = /[^\w\s.|`]/g, fu = function (n) {
            return n.replace(uu, "\\$&")
        };
        i.event = {
            add: function (n, r, u, f) {
                var y, s, c, l, h, e, w, a, p, v;
                if (n.nodeType !== 3 && n.nodeType !== 8) {
                    if (u === !1)u = o; else if (!u)return;
                    if (u.handler && (y = u, u = y.handler), u.guid || (u.guid = i.guid++), c = i._data(n), c) {
                        for (l = c.events, h = c.handle, l || (c.events = l = {}), h || (c.handle = h = function (n) {
                            return typeof i != "undefined" && (!n || i.event.triggered !== n.type) ? i.event.handle.apply(h.elem, arguments) : t
                        }), h.elem = n, r = r.split(" "), w = 0; e = r[w++];)s = y ? i.extend({}, y) : {
                            handler: u,
                            data: f
                        }, e.indexOf(".") > -1 ? (a = e.split("."), e = a.shift(), s.namespace = a.slice(0).sort().join(".")) : (a = [], s.namespace = ""), s.type = e, s.guid || (s.guid = u.guid), p = l[e], v = i.event.special[e] || {}, p || (p = l[e] = [], v.setup && v.setup.call(n, f, a, h) !== !1 || (n.addEventListener ? n.addEventListener(e, h, !1) : n.attachEvent && n.attachEvent("on" + e, h))), v.add && (v.add.call(n, s), s.handler.guid || (s.handler.guid = u.guid)), p.push(s), i.event.global[e] = !0;
                        n = null
                    }
                }
            },
            global: {},
            remove: function (n, r, u, f) {
                var b;
                if (n.nodeType !== 3 && n.nodeType !== 8) {
                    u === !1 && (u = o);
                    var d, e, s, g = 0, p, a, w, v, h, c, k, l = i.hasData(n) && i._data(n), y = l && l.events;
                    if (l && y) {
                        if (r && r.type && (u = r.handler, r = r.type), !r || typeof r == "string" && r.charAt(0) === ".") {
                            r = r || "";
                            for (e in y)i.event.remove(n, e + r);
                            return
                        }
                        for (r = r.split(" "); e = r[g++];)if (k = e, c = null, p = e.indexOf(".") < 0, a = [], p || (a = e.split("."), e = a.shift(), w = new RegExp("(^|\\.)" + i.map(a.slice(0).sort(), fu).join("\\.(?:.*\\.)?") + "(\\.|$)")), h = y[e], h) {
                            if (!u) {
                                for (s = 0; s < h.length; s++)c = h[s], (p || w.test(c.namespace)) && (i.event.remove(n, k, c.handler, s), h.splice(s--, 1));
                                continue
                            }
                            for (v = i.event.special[e] || {}, s = f || 0; s < h.length; s++)if (c = h[s], u.guid === c.guid && ((p || w.test(c.namespace)) && (f == null && h.splice(s--, 1), v.remove && v.remove.call(n, c)), f != null))break;
                            (h.length === 0 || f != null && h.length === 1) && (v.teardown && v.teardown.call(n, a) !== !1 || i.removeEvent(n, e, l.handle), d = null, delete y[e])
                        }
                        i.isEmptyObject(y) && (b = l.handle, b && (b.elem = null), delete l.events, delete l.handle, i.isEmptyObject(l) && i.removeData(n, t, !0))
                    }
                }
            },
            customEvent: {getData: !0, setData: !0, changeData: !0},
            trigger: function (r, u, f, e) {
                var o = r.type || r, c = [], y, s, h, a, l, v;
                if (o.indexOf("!") >= 0 && (o = o.slice(0, -1), y = !0), o.indexOf(".") >= 0 && (c = o.split("."), o = c.shift(), c.sort()), f && !i.event.customEvent[o] || i.event.global[o]) {
                    if (r = typeof r == "object" ? r[i.expando] ? r : new i.Event(o, r) : new i.Event(o), r.type = o, r.exclusive = y, r.namespace = c.join("."), r.namespace_re = new RegExp("(^|\\.)" + c.join("\\.(?:.*\\.)?") + "(\\.|$)"), (e || !f) && (r.preventDefault(), r.stopPropagation()), !f) {
                        i.each(i.cache, function () {
                            var t = i.expando, n = this[t];
                            n && n.events && n.events[o] && i.event.trigger(r, u, n.handle.elem)
                        });
                        return
                    }
                    if (f.nodeType !== 3 && f.nodeType !== 8) {
                        r.result = t;
                        r.target = f;
                        u = u != null ? i.makeArray(u) : [];
                        u.unshift(r);
                        s = f;
                        h = o.indexOf(":") < 0 ? "on" + o : "";
                        do a = i._data(s, "handle"), r.currentTarget = s, a && a.apply(s, u), h && i.acceptData(s) && s[h] && s[h].apply(s, u) === !1 && (r.result = !1, r.preventDefault()), s = s.parentNode || s.ownerDocument || s === r.target.ownerDocument && n; while (s && !r.isPropagationStopped());
                        if (!r.isDefaultPrevented() && (v = i.event.special[o] || {}, (!v._default || v._default.call(f.ownerDocument, r) === !1) && !(o === "click" && i.nodeName(f, "a")) && i.acceptData(f))) {
                            try {
                                h && f[o] && (l = f[h], l && (f[h] = null), i.event.triggered = o, f[o]())
                            } catch (p) {
                            }
                            l && (f[h] = l);
                            i.event.triggered = t
                        }
                        return r.result
                    }
                }
            },
            handle: function (r) {
                var f, h, u, e;
                r = i.event.fix(r || n.event);
                var o = ((i._data(this, "events") || {})[r.type] || []).slice(0), c = !r.exclusive && !r.namespace, s = Array.prototype.slice.call(arguments, 0);
                for (s[0] = r, r.currentTarget = this, f = 0, h = o.length; f < h; f++)if (u = o[f], (c || r.namespace_re.test(u.namespace)) && (r.handler = u.handler, r.data = u.data, r.handleObj = u, e = u.handler.apply(this, s), e !== t && (r.result = e, e === !1 && (r.preventDefault(), r.stopPropagation())), r.isImmediatePropagationStopped()))break;
                return r.result
            },
            props: "altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode layerX layerY metaKey newValue offsetX offsetY pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which".split(" "),
            fix: function (n) {
                var e, o, s;
                if (n[i.expando])return n;
                for (e = n, n = i.Event(e), o = this.props.length; o;)s = this.props[--o], n[s] = e[s];
                if (n.target || (n.target = n.srcElement || r), n.target.nodeType === 3 && (n.target = n.target.parentNode), !n.relatedTarget && n.fromElement && (n.relatedTarget = n.fromElement === n.target ? n.toElement : n.fromElement), n.pageX == null && n.clientX != null) {
                    var h = n.target.ownerDocument || r, u = h.documentElement, f = h.body;
                    n.pageX = n.clientX + (u && u.scrollLeft || f && f.scrollLeft || 0) - (u && u.clientLeft || f && f.clientLeft || 0);
                    n.pageY = n.clientY + (u && u.scrollTop || f && f.scrollTop || 0) - (u && u.clientTop || f && f.clientTop || 0)
                }
                return n.which == null && (n.charCode != null || n.keyCode != null) && (n.which = n.charCode != null ? n.charCode : n.keyCode), !n.metaKey && n.ctrlKey && (n.metaKey = n.ctrlKey), n.which || n.button === t || (n.which = n.button & 1 ? 1 : n.button & 2 ? 3 : n.button & 4 ? 2 : 0), n
            },
            guid: 1e8,
            proxy: i.proxy,
            special: {
                ready: {setup: i.bindReady, teardown: i.noop}, live: {
                    add: function (n) {
                        i.event.add(this, d(n.origType, n.selector), i.extend({}, n, {
                            handler: eu,
                            guid: n.handler.guid
                        }))
                    }, remove: function (n) {
                        i.event.remove(this, d(n.origType, n.selector), n)
                    }
                }, beforeunload: {
                    setup: function (n, t, r) {
                        i.isWindow(this) && (this.onbeforeunload = r)
                    }, teardown: function (n, t) {
                        this.onbeforeunload === t && (this.onbeforeunload = null)
                    }
                }
            }
        };
        i.removeEvent = r.removeEventListener ? function (n, t, i) {
            n.removeEventListener && n.removeEventListener(t, i, !1)
        } : function (n, t, i) {
            n.detachEvent && n.detachEvent("on" + t, i)
        };
        i.Event = function (n, t) {
            if (!this.preventDefault)return new i.Event(n, t);
            n && n.type ? (this.originalEvent = n, this.type = n.type, this.isDefaultPrevented = n.defaultPrevented || n.returnValue === !1 || n.getPreventDefault && n.getPreventDefault() ? b : o) : this.type = n;
            t && i.extend(this, t);
            this.timeStamp = i.now();
            this[i.expando] = !0
        };
        i.Event.prototype = {
            preventDefault: function () {
                this.isDefaultPrevented = b;
                var n = this.originalEvent;
                n && (n.preventDefault ? n.preventDefault() : n.returnValue = !1)
            }, stopPropagation: function () {
                this.isPropagationStopped = b;
                var n = this.originalEvent;
                n && (n.stopPropagation && n.stopPropagation(), n.cancelBubble = !0)
            }, stopImmediatePropagation: function () {
                this.isImmediatePropagationStopped = b;
                this.stopPropagation()
            }, isDefaultPrevented: o, isPropagationStopped: o, isImmediatePropagationStopped: o
        };
        ht = function (n) {
            var t = n.relatedTarget, r = !1, u = n.type;
            n.type = n.data;
            t !== this && (t && (r = i.contains(this, t)), r || (i.event.handle.apply(this, arguments), n.type = u))
        };
        ct = function (n) {
            n.type = n.data;
            i.event.handle.apply(this, arguments)
        };
        i.each({mouseenter: "mouseover", mouseleave: "mouseout"}, function (n, t) {
            i.event.special[n] = {
                setup: function (r) {
                    i.event.add(this, t, r && r.selector ? ct : ht, n)
                }, teardown: function (n) {
                    i.event.remove(this, t, n && n.selector ? ct : ht)
                }
            }
        });
        i.support.submitBubbles || (i.event.special.submit = {
            setup: function () {
                if (i.nodeName(this, "form"))return !1;
                i.event.add(this, "click.specialSubmit", function (n) {
                    var t = n.target, r = i.nodeName(t, "input") || i.nodeName(t, "button") ? t.type : "";
                    (r === "submit" || r === "image") && i(t).closest("form").length && ei("submit", this, arguments)
                });
                i.event.add(this, "keypress.specialSubmit", function (n) {
                    var t = n.target, r = i.nodeName(t, "input") || i.nodeName(t, "button") ? t.type : "";
                    (r === "text" || r === "password") && i(t).closest("form").length && n.keyCode === 13 && ei("submit", this, arguments)
                })
            }, teardown: function () {
                i.event.remove(this, ".specialSubmit")
            }
        });
        i.support.changeBubbles || (lt = function (n) {
            var r = i.nodeName(n, "input") ? n.type : "", t = n.value;
            return r === "radio" || r === "checkbox" ? t = n.checked : r === "select-multiple" ? t = n.selectedIndex > -1 ? i.map(n.options, function (n) {
                return n.selected
            }).join("-") : "" : i.nodeName(n, "select") && (t = n.selectedIndex), t
        }, p = function (n) {
            var r = n.target, u, f;
            st.test(r.nodeName) && !r.readOnly && (u = i._data(r, "_change_data"), f = lt(r), (n.type !== "focusout" || r.type !== "radio") && i._data(r, "_change_data", f), u !== t && f !== u) && (u != null || f) && (n.type = "change", n.liveFired = t, i.event.trigger(n, arguments[1], r))
        }, i.event.special.change = {
            filters: {
                focusout: p, beforedeactivate: p, click: function (n) {
                    var t = n.target, r = i.nodeName(t, "input") ? t.type : "";
                    (r === "radio" || r === "checkbox" || i.nodeName(t, "select")) && p.call(this, n)
                }, keydown: function (n) {
                    var t = n.target, r = i.nodeName(t, "input") ? t.type : "";
                    (n.keyCode !== 13 || i.nodeName(t, "textarea")) && (n.keyCode !== 32 || r !== "checkbox" && r !== "radio") && r !== "select-multiple" || p.call(this, n)
                }, beforeactivate: function (n) {
                    var t = n.target;
                    i._data(t, "_change_data", lt(t))
                }
            }, setup: function () {
                if (this.type === "file")return !1;
                for (var n in y)i.event.add(this, n + ".specialChange", y[n]);
                return st.test(this.nodeName)
            }, teardown: function () {
                return i.event.remove(this, ".specialChange"), st.test(this.nodeName)
            }
        }, y = i.event.special.change.filters, y.focus = y.beforeactivate);
        i.support.focusinBubbles || i.each({focus: "focusin", blur: "focusout"}, function (n, t) {
            function f(n) {
                var r = i.event.fix(n);
                r.type = t;
                r.originalEvent = {};
                i.event.trigger(r, null, r.target);
                r.isDefaultPrevented() && n.preventDefault()
            }

            var u = 0;
            i.event.special[t] = {
                setup: function () {
                    u++ == 0 && r.addEventListener(n, f, !0)
                }, teardown: function () {
                    --u == 0 && r.removeEventListener(n, f, !0)
                }
            }
        });
        i.each(["bind", "one"], function (n, r) {
            i.fn[r] = function (n, u, f) {
                var e, s, o, h;
                if (typeof n == "object") {
                    for (s in n)this[r](s, u, n[s], f);
                    return this
                }
                if ((arguments.length === 2 || u === !1) && (f = u, u = t), r === "one" ? (e = function (n) {
                        return i(this).unbind(n, e), f.apply(this, arguments)
                    }, e.guid = f.guid || i.guid++) : e = f, n === "unload" && r !== "one")this.one(n, u, f); else for (o = 0, h = this.length; o < h; o++)i.event.add(this[o], n, e, u);
                return this
            }
        });
        i.fn.extend({
            unbind: function (n, t) {
                var u, r, f;
                if (typeof n != "object" || n.preventDefault)for (r = 0, f = this.length; r < f; r++)i.event.remove(this[r], n, t); else for (u in n)this.unbind(u, n[u]);
                return this
            }, delegate: function (n, t, i, r) {
                return this.live(t, i, r, n)
            }, undelegate: function (n, t, i) {
                return arguments.length === 0 ? this.unbind("live") : this.die(t, null, i, n)
            }, trigger: function (n, t) {
                return this.each(function () {
                    i.event.trigger(n, t, this)
                })
            }, triggerHandler: function (n, t) {
                if (this[0])return i.event.trigger(n, t, this[0], !0)
            }, toggle: function (n) {
                var t = arguments, u = n.guid || i.guid++, r = 0, f = function (u) {
                    var f = (i.data(this, "lastToggle" + n.guid) || 0) % r;
                    return i.data(this, "lastToggle" + n.guid, f + 1), u.preventDefault(), t[f].apply(this, arguments) || !1
                };
                for (f.guid = u; r < t.length;)t[r++].guid = u;
                return this.click(f)
            }, hover: function (n, t) {
                return this.mouseenter(n).mouseleave(t || n)
            }
        });
        k = {focus: "focusin", blur: "focusout", mouseenter: "mouseover", mouseleave: "mouseout"};
        i.each(["live", "die"], function (n, r) {
            i.fn[r] = function (n, u, f, e) {
                var s, b = 0, v, h, p, l = e || this.selector, c = e ? this : i(this.context), y, a, w;
                if (typeof n == "object" && !n.preventDefault) {
                    for (y in n)c[r](y, u, n[y], l);
                    return this
                }
                if (r === "die" && !n && e && e.charAt(0) === ".")return c.unbind(e), this;
                for ((u === !1 || i.isFunction(u)) && (f = u || o, u = t), n = (n || "").split(" "); (s = n[b++]) != null;) {
                    if (v = ot.exec(s), h = "", v && (h = v[0], s = s.replace(ot, "")), s === "hover") {
                        n.push("mouseenter" + h, "mouseleave" + h);
                        continue
                    }
                    if (p = s, k[s] ? (n.push(k[s] + h), s = s + h) : s = (k[s] || s) + h, r === "live")for (a = 0, w = c.length; a < w; a++)i.event.add(c[a], "live." + d(s, l), {
                        data: u,
                        selector: l,
                        handler: f,
                        origType: s,
                        origHandler: f,
                        preType: p
                    }); else c.unbind("live." + d(s, l), f)
                }
                return this
            }
        });
        i.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error".split(" "), function (n, t) {
            i.fn[t] = function (n, i) {
                return i == null && (i = n, n = null), arguments.length > 0 ? this.bind(t, n, i) : this.trigger(t)
            };
            i.attrFn && (i.attrFn[t] = !0)
        }), function () {
            function b(n, t, i, r, u, f) {
                for (var e, s, o = 0, h = r.length; o < h; o++)if (e = r[o], e) {
                    for (s = !1, e = e[n]; e;) {
                        if (e.sizcache === i) {
                            s = r[e.sizset];
                            break
                        }
                        if (e.nodeType !== 1 || f || (e.sizcache = i, e.sizset = o), e.nodeName.toLowerCase() === t) {
                            s = e;
                            break
                        }
                        e = e[n]
                    }
                    r[o] = s
                }
            }

            function k(t, i, r, u, f, e) {
                for (var o, h, s = 0, c = u.length; s < c; s++)if (o = u[s], o) {
                    for (h = !1, o = o[t]; o;) {
                        if (o.sizcache === r) {
                            h = u[o.sizset];
                            break
                        }
                        if (o.nodeType === 1)if (e || (o.sizcache = r, o.sizset = s), typeof i != "string") {
                            if (o === i) {
                                h = !0;
                                break
                            }
                        } else if (n.filter(i, [o]).length > 0) {
                            h = o;
                            break
                        }
                        o = o[t]
                    }
                    u[s] = h
                }
            }

            var a = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g, v = 0, p = Object.prototype.toString, h = !1, w = !0, e = /\\/g, c = /\W/, n, o, f, l, s, y;
            [0, 0].sort(function () {
                return w = !1, 0
            });
            n = function (t, i, e, o) {
                var tt;
                if (e = e || [], i = i || r, tt = i, i.nodeType !== 1 && i.nodeType !== 9)return [];
                if (!t || typeof t != "string")return e;
                var w, v, h, nt, l, b, k, c, it = !0, g = n.isXML(i), s = [], rt = t;
                do if (a.exec(""), w = a.exec(rt), w && (rt = w[3], s.push(w[1]), w[2])) {
                    nt = w[3];
                    break
                } while (w);
                if (s.length > 1 && d.exec(t))if (s.length === 2 && u.relative[s[0]])v = y(s[0] + s[1], i); else for (v = u.relative[s[0]] ? [i] : n(s.shift(), i); s.length;)t = s.shift(), u.relative[t] && (t += s.shift()), v = y(t, v); else if (!o && s.length > 1 && i.nodeType === 9 && !g && u.match.ID.test(s[0]) && !u.match.ID.test(s[s.length - 1]) && (l = n.find(s.shift(), i, g), i = l.expr ? n.filter(l.expr, l.set)[0] : l.set[0]), i)for (l = o ? {
                    expr: s.pop(),
                    set: f(o)
                } : n.find(s.pop(), s.length === 1 && (s[0] === "~" || s[0] === "+") && i.parentNode ? i.parentNode : i, g), v = l.expr ? n.filter(l.expr, l.set) : l.set, s.length > 0 ? h = f(v) : it = !1; s.length;)b = s.pop(), k = b, u.relative[b] ? k = s.pop() : b = "", k == null && (k = i), u.relative[b](h, k, g); else h = s = [];
                if (h || (h = v), h || n.error(b || t), p.call(h) === "[object Array]")if (it)if (i && i.nodeType === 1)for (c = 0; h[c] != null; c++)h[c] && (h[c] === !0 || h[c].nodeType === 1 && n.contains(i, h[c])) && e.push(v[c]); else for (c = 0; h[c] != null; c++)h[c] && h[c].nodeType === 1 && e.push(v[c]); else e.push.apply(e, h); else f(h, e);
                return nt && (n(nt, tt, e, o), n.uniqueSort(e)), e
            };
            n.uniqueSort = function (n) {
                if (l && (h = w, n.sort(l), h))for (var t = 1; t < n.length; t++)n[t] === n[t - 1] && n.splice(t--, 1);
                return n
            };
            n.matches = function (t, i) {
                return n(t, null, null, i)
            };
            n.matchesSelector = function (t, i) {
                return n(i, null, null, [t]).length > 0
            };
            n.find = function (n, t, i) {
                var f, o, c, r, s, h;
                if (!n)return [];
                for (o = 0, c = u.order.length; o < c; o++)if (s = u.order[o], (r = u.leftMatch[s].exec(n)) && (h = r[1], r.splice(1, 1), h.substr(h.length - 1) !== "\\" && (r[1] = (r[1] || "").replace(e, ""), f = u.find[s](r, t, i), f != null))) {
                    n = n.replace(u.match[s], "");
                    break
                }
                return f || (f = typeof t.getElementsByTagName != "undefined" ? t.getElementsByTagName("*") : []), {
                    set: f,
                    expr: n
                }
            };
            n.filter = function (i, r, f, e) {
                for (var o, h, b = i, l = [], s = r, d = r && r[0] && n.isXML(r[0]), c, a, y, k, p, v, w; i && r.length;) {
                    for (c in u.filter)if ((o = u.leftMatch[c].exec(i)) != null && o[2]) {
                        if (k = u.filter[c], p = o[1], h = !1, o.splice(1, 1), p.substr(p.length - 1) === "\\")continue;
                        if (s === l && (l = []), u.preFilter[c])if (o = u.preFilter[c](o, s, f, l, e, d), o) {
                            if (o === !0)continue
                        } else h = a = !0;
                        if (o)for (v = 0; (y = s[v]) != null; v++)y && (a = k(y, o, v, s), w = e ^ !!a, f && a != null ? w ? h = !0 : s[v] = !1 : w && (l.push(y), h = !0));
                        if (a !== t) {
                            if (f || (s = l), i = i.replace(u.match[c], ""), !h)return [];
                            break
                        }
                    }
                    if (i === b)if (h == null)n.error(i); else break;
                    b = i
                }
                return s
            };
            n.error = function (n) {
                throw"Syntax error, unrecognized expression: " + n;
            };
            var u = n.selectors = {
                order: ["ID", "NAME", "TAG"],
                match: {
                    ID: /#((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
                    CLASS: /\.((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
                    NAME: /\[name=['"]*((?:[\w\u00c0-\uFFFF\-]|\\.)+)['"]*\]/,
                    ATTR: /\[\s*((?:[\w\u00c0-\uFFFF\-]|\\.)+)\s*(?:(\S?=)\s*(?:(['"])(.*?)\3|(#?(?:[\w\u00c0-\uFFFF\-]|\\.)*)|)|)\s*\]/,
                    TAG: /^((?:[\w\u00c0-\uFFFF\*\-]|\\.)+)/,
                    CHILD: /:(only|nth|last|first)-child(?:\(\s*(even|odd|(?:[+\-]?\d+|(?:[+\-]?\d*)?n\s*(?:[+\-]\s*\d+)?))\s*\))?/,
                    POS: /:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^\-]|$)/,
                    PSEUDO: /:((?:[\w\u00c0-\uFFFF\-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/
                },
                leftMatch: {},
                attrMap: {"class": "className", "for": "htmlFor"},
                attrHandle: {
                    href: function (n) {
                        return n.getAttribute("href")
                    }, type: function (n) {
                        return n.getAttribute("type")
                    }
                },
                relative: {
                    "+": function (t, i) {
                        var f = typeof i == "string", e = f && !c.test(i), o = f && !e, u, s, r;
                        for (e && (i = i.toLowerCase()), u = 0, s = t.length; u < s; u++)if (r = t[u]) {
                            while ((r = r.previousSibling) && r.nodeType !== 1);
                            t[u] = o || r && r.nodeName.toLowerCase() === i ? r || !1 : r === i
                        }
                        o && n.filter(i, t, !0)
                    }, ">": function (t, i) {
                        var u, f = typeof i == "string", r = 0, o = t.length, e;
                        if (f && !c.test(i))for (i = i.toLowerCase(); r < o; r++)u = t[r], u && (e = u.parentNode, t[r] = e.nodeName.toLowerCase() === i ? e : !1); else {
                            for (; r < o; r++)u = t[r], u && (t[r] = f ? u.parentNode : u.parentNode === i);
                            f && n.filter(i, t, !0)
                        }
                    }, "": function (n, t, i) {
                        var r, f = v++, u = k;
                        typeof t != "string" || c.test(t) || (t = t.toLowerCase(), r = t, u = b);
                        u("parentNode", t, f, n, r, i)
                    }, "~": function (n, t, i) {
                        var r, f = v++, u = k;
                        typeof t != "string" || c.test(t) || (t = t.toLowerCase(), r = t, u = b);
                        u("previousSibling", t, f, n, r, i)
                    }
                },
                find: {
                    ID: function (n, t, i) {
                        if (typeof t.getElementById != "undefined" && !i) {
                            var r = t.getElementById(n[1]);
                            return r && r.parentNode ? [r] : []
                        }
                    }, NAME: function (n, t) {
                        var r, u, i, f;
                        if (typeof t.getElementsByName != "undefined") {
                            for (r = [], u = t.getElementsByName(n[1]), i = 0, f = u.length; i < f; i++)u[i].getAttribute("name") === n[1] && r.push(u[i]);
                            return r.length === 0 ? null : r
                        }
                    }, TAG: function (n, t) {
                        if (typeof t.getElementsByTagName != "undefined")return t.getElementsByTagName(n[1])
                    }
                },
                preFilter: {
                    CLASS: function (n, t, i, r, u, f) {
                        if (n = " " + n[1].replace(e, "") + " ", f)return n;
                        for (var s = 0, o; (o = t[s]) != null; s++)o && (u ^ (o.className && (" " + o.className + " ").replace(/[\t\n\r]/g, " ").indexOf(n) >= 0) ? i || r.push(o) : i && (t[s] = !1));
                        return !1
                    }, ID: function (n) {
                        return n[1].replace(e, "")
                    }, TAG: function (n) {
                        return n[1].replace(e, "").toLowerCase()
                    }, CHILD: function (t) {
                        if (t[1] === "nth") {
                            t[2] || n.error(t[0]);
                            t[2] = t[2].replace(/^\+|\s*/g, "");
                            var i = /(-?)(\d*)(?:n([+\-]?\d*))?/.exec(t[2] === "even" && "2n" || t[2] === "odd" && "2n+1" || !/\D/.test(t[2]) && "0n+" + t[2] || t[2]);
                            t[2] = i[1] + (i[2] || 1) - 0;
                            t[3] = i[3] - 0
                        } else t[2] && n.error(t[0]);
                        return t[0] = v++, t
                    }, ATTR: function (n, t, i, r, f, o) {
                        var s = n[1] = n[1].replace(e, "");
                        return !o && u.attrMap[s] && (n[1] = u.attrMap[s]), n[4] = (n[4] || n[5] || "").replace(e, ""), n[2] === "~=" && (n[4] = " " + n[4] + " "), n
                    }, PSEUDO: function (t, i, r, f, e) {
                        if (t[1] === "not")if ((a.exec(t[3]) || "").length > 1 || /^\w/.test(t[3]))t[3] = n(t[3], null, null, i); else {
                            var o = n.filter(t[3], i, r, !0 ^ e);
                            return r || f.push.apply(f, o), !1
                        } else if (u.match.POS.test(t[0]) || u.match.CHILD.test(t[0]))return !0;
                        return t
                    }, POS: function (n) {
                        return n.unshift(!0), n
                    }
                },
                filters: {
                    enabled: function (n) {
                        return n.disabled === !1 && n.type !== "hidden"
                    }, disabled: function (n) {
                        return n.disabled === !0
                    }, checked: function (n) {
                        return n.checked === !0
                    }, selected: function (n) {
                        return n.parentNode && n.parentNode.selectedIndex, n.selected === !0
                    }, parent: function (n) {
                        return !!n.firstChild
                    }, empty: function (n) {
                        return !n.firstChild
                    }, has: function (t, i, r) {
                        return !!n(r[3], t).length
                    }, header: function (n) {
                        return /h\d/i.test(n.nodeName)
                    }, text: function (n) {
                        var t = n.getAttribute("type"), i = n.type;
                        return n.nodeName.toLowerCase() === "input" && "text" === i && (t === i || t === null)
                    }, radio: function (n) {
                        return n.nodeName.toLowerCase() === "input" && "radio" === n.type
                    }, checkbox: function (n) {
                        return n.nodeName.toLowerCase() === "input" && "checkbox" === n.type
                    }, file: function (n) {
                        return n.nodeName.toLowerCase() === "input" && "file" === n.type
                    }, password: function (n) {
                        return n.nodeName.toLowerCase() === "input" && "password" === n.type
                    }, submit: function (n) {
                        var t = n.nodeName.toLowerCase();
                        return (t === "input" || t === "button") && "submit" === n.type
                    }, image: function (n) {
                        return n.nodeName.toLowerCase() === "input" && "image" === n.type
                    }, reset: function (n) {
                        var t = n.nodeName.toLowerCase();
                        return (t === "input" || t === "button") && "reset" === n.type
                    }, button: function (n) {
                        var t = n.nodeName.toLowerCase();
                        return t === "input" && "button" === n.type || t === "button"
                    }, input: function (n) {
                        return /input|select|textarea|button/i.test(n.nodeName)
                    }, focus: function (n) {
                        return n === n.ownerDocument.activeElement
                    }
                },
                setFilters: {
                    first: function (n, t) {
                        return t === 0
                    }, last: function (n, t, i, r) {
                        return t === r.length - 1
                    }, even: function (n, t) {
                        return t % 2 == 0
                    }, odd: function (n, t) {
                        return t % 2 == 1
                    }, lt: function (n, t, i) {
                        return t < i[3] - 0
                    }, gt: function (n, t, i) {
                        return t > i[3] - 0
                    }, nth: function (n, t, i) {
                        return i[3] - 0 === t
                    }, eq: function (n, t, i) {
                        return i[3] - 0 === t
                    }
                },
                filter: {
                    PSEUDO: function (t, i, r, f) {
                        var e = i[1], h = u.filters[e], s, o, c;
                        if (h)return h(t, r, i, f);
                        if (e === "contains")return (t.textContent || t.innerText || n.getText([t]) || "").indexOf(i[3]) >= 0;
                        if (e === "not") {
                            for (s = i[3], o = 0, c = s.length; o < c; o++)if (s[o] === t)return !1;
                            return !0
                        }
                        n.error(e)
                    }, CHILD: function (n, t) {
                        var s = t[1], i = n, r, e, o, u, h, f;
                        switch (s) {
                            case"only":
                            case"first":
                                while (i = i.previousSibling)if (i.nodeType === 1)return !1;
                                if (s === "first")return !0;
                                i = n;
                            case"last":
                                while (i = i.nextSibling)if (i.nodeType === 1)return !1;
                                return !0;
                            case"nth":
                                if (r = t[2], e = t[3], r === 1 && e === 0)return !0;
                                if (o = t[0], u = n.parentNode, u && (u.sizcache !== o || !n.nodeIndex)) {
                                    for (h = 0, i = u.firstChild; i; i = i.nextSibling)i.nodeType === 1 && (i.nodeIndex = ++h);
                                    u.sizcache = o
                                }
                                return f = n.nodeIndex - e, r === 0 ? f === 0 : f % r == 0 && f / r >= 0
                        }
                    }, ID: function (n, t) {
                        return n.nodeType === 1 && n.getAttribute("id") === t
                    }, TAG: function (n, t) {
                        return t === "*" && n.nodeType === 1 || n.nodeName.toLowerCase() === t
                    }, CLASS: function (n, t) {
                        return (" " + (n.className || n.getAttribute("class")) + " ").indexOf(t) > -1
                    }, ATTR: function (n, t) {
                        var e = t[1], o = u.attrHandle[e] ? u.attrHandle[e](n) : n[e] != null ? n[e] : n.getAttribute(e), r = o + "", f = t[2], i = t[4];
                        return o == null ? f === "!=" : f === "=" ? r === i : f === "*=" ? r.indexOf(i) >= 0 : f === "~=" ? (" " + r + " ").indexOf(i) >= 0 : i ? f === "!=" ? r !== i : f === "^=" ? r.indexOf(i) === 0 : f === "$=" ? r.substr(r.length - i.length) === i : f === "|=" ? r === i || r.substr(0, i.length + 1) === i + "-" : !1 : r && o !== !1
                    }, POS: function (n, t, i, r) {
                        var e = t[2], f = u.setFilters[e];
                        if (f)return f(n, i, t, r)
                    }
                }
            }, d = u.match.POS, g = function (n, t) {
                return "\\" + (+t + 1)
            };
            for (o in u.match)u.match[o] = new RegExp(u.match[o].source + /(?![^\[]*\])(?![^\(]*\))/.source), u.leftMatch[o] = new RegExp(/(^(?:.|\r|\n)*?)/.source + u.match[o].source.replace(/\\(\d+)/g, g));
            f = function (n, t) {
                return (n = Array.prototype.slice.call(n, 0), t) ? (t.push.apply(t, n), t) : n
            };
            try {
                Array.prototype.slice.call(r.documentElement.childNodes, 0)[0].nodeType
            } catch (nt) {
                f = function (n, t) {
                    var i = 0, r = t || [], u;
                    if (p.call(n) === "[object Array]")Array.prototype.push.apply(r, n); else if (typeof n.length == "number")for (u = n.length; i < u; i++)r.push(n[i]); else for (; n[i]; i++)r.push(n[i]);
                    return r
                }
            }
            r.documentElement.compareDocumentPosition ? l = function (n, t) {
                return n === t ? (h = !0, 0) : !n.compareDocumentPosition || !t.compareDocumentPosition ? n.compareDocumentPosition ? -1 : 1 : n.compareDocumentPosition(t) & 4 ? -1 : 1
            } : (l = function (n, t) {
                var i;
                if (n === t)return h = !0, 0;
                if (n.sourceIndex && t.sourceIndex)return n.sourceIndex - t.sourceIndex;
                var e, l, u = [], f = [], o = n.parentNode, c = t.parentNode, r = o;
                if (o === c)return s(n, t);
                if (o) {
                    if (!c)return 1
                } else return -1;
                while (r)u.unshift(r), r = r.parentNode;
                for (r = c; r;)f.unshift(r), r = r.parentNode;
                for (e = u.length, l = f.length, i = 0; i < e && i < l; i++)if (u[i] !== f[i])return s(u[i], f[i]);
                return i === e ? s(n, f[i], -1) : s(u[i], t, 1)
            }, s = function (n, t, i) {
                if (n === t)return i;
                for (var r = n.nextSibling; r;) {
                    if (r === t)return -1;
                    r = r.nextSibling
                }
                return 1
            });
            n.getText = function (t) {
                for (var u = "", i, r = 0; t[r]; r++)i = t[r], i.nodeType === 3 || i.nodeType === 4 ? u += i.nodeValue : i.nodeType !== 8 && (u += n.getText(i.childNodes));
                return u
            }, function () {
                var n = r.createElement("div"), f = "script" + (new Date).getTime(), i = r.documentElement;
                n.innerHTML = "<a name='" + f + "'/>";
                i.insertBefore(n, i.firstChild);
                r.getElementById(f) && (u.find.ID = function (n, i, r) {
                    if (typeof i.getElementById != "undefined" && !r) {
                        var u = i.getElementById(n[1]);
                        return u ? u.id === n[1] || typeof u.getAttributeNode != "undefined" && u.getAttributeNode("id").nodeValue === n[1] ? [u] : t : []
                    }
                }, u.filter.ID = function (n, t) {
                    var i = typeof n.getAttributeNode != "undefined" && n.getAttributeNode("id");
                    return n.nodeType === 1 && i && i.nodeValue === t
                });
                i.removeChild(n);
                i = n = null
            }(), function () {
                var n = r.createElement("div");
                n.appendChild(r.createComment(""));
                n.getElementsByTagName("*").length > 0 && (u.find.TAG = function (n, t) {
                    var i = t.getElementsByTagName(n[1]), u, r;
                    if (n[1] === "*") {
                        for (u = [], r = 0; i[r]; r++)i[r].nodeType === 1 && u.push(i[r]);
                        i = u
                    }
                    return i
                });
                n.innerHTML = "<a href='#'><\/a>";
                n.firstChild && typeof n.firstChild.getAttribute != "undefined" && n.firstChild.getAttribute("href") !== "#" && (u.attrHandle.href = function (n) {
                    return n.getAttribute("href", 2)
                });
                n = null
            }();
            r.querySelectorAll && function () {
                var i = n, t = r.createElement("div"), o = "__sizzle__", e;
                if (t.innerHTML = "<p class='TEST'><\/p>", !t.querySelectorAll || t.querySelectorAll(".TEST").length !== 0) {
                    n = function (t, e, s, h) {
                        var c, l;
                        if (e = e || r, !h && !n.isXML(e)) {
                            if (c = /^(\w+$)|^\.([\w\-]+$)|^#([\w\-]+$)/.exec(t), c && (e.nodeType === 1 || e.nodeType === 9)) {
                                if (c[1])return f(e.getElementsByTagName(t), s);
                                if (c[2] && u.find.CLASS && e.getElementsByClassName)return f(e.getElementsByClassName(c[2]), s)
                            }
                            if (e.nodeType === 9) {
                                if (t === "body" && e.body)return f([e.body], s);
                                if (c && c[3])if (l = e.getElementById(c[3]), l && l.parentNode) {
                                    if (l.id === c[3])return f([l], s)
                                } else return f([], s);
                                try {
                                    return f(e.querySelectorAll(t), s)
                                } catch (b) {
                                }
                            } else if (e.nodeType === 1 && e.nodeName.toLowerCase() !== "object") {
                                var w = e, v = e.getAttribute("id"), a = v || o, y = e.parentNode, p = /^\s*[+~]/.test(t);
                                v ? a = a.replace(/'/g, "\\$&") : e.setAttribute("id", a);
                                p && y && (e = e.parentNode);
                                try {
                                    if (!p || y)return f(e.querySelectorAll("[id='" + a + "'] " + t), s)
                                } catch (k) {
                                } finally {
                                    v || w.removeAttribute("id")
                                }
                            }
                        }
                        return i(t, e, s, h)
                    };
                    for (e in i)n[e] = i[e];
                    t = null
                }
            }(), function () {
                var t = r.documentElement, i = t.matchesSelector || t.mozMatchesSelector || t.webkitMatchesSelector || t.msMatchesSelector, e, f;
                if (i) {
                    e = !i.call(r.createElement("div"), "div");
                    f = !1;
                    try {
                        i.call(r.documentElement, "[test!='']:sizzle")
                    } catch (o) {
                        f = !0
                    }
                    n.matchesSelector = function (t, r) {
                        if (r = r.replace(/\=\s*([^'"\]]*)\s*\]/g, "='$1']"), !n.isXML(t))try {
                            if (f || !u.match.PSEUDO.test(r) && !/!=/.test(r)) {
                                var o = i.call(t, r);
                                if (o || !e || t.document && t.document.nodeType !== 11)return o
                            }
                        } catch (s) {
                        }
                        return n(r, null, null, [t]).length > 0
                    }
                }
            }(), function () {
                var n = r.createElement("div");
                (n.innerHTML = "<div class='test e'><\/div><div class='test'><\/div>", n.getElementsByClassName && n.getElementsByClassName("e").length !== 0) && (n.lastChild.className = "e", n.getElementsByClassName("e").length !== 1) && (u.order.splice(1, 0, "CLASS"), u.find.CLASS = function (n, t, i) {
                    if (typeof t.getElementsByClassName != "undefined" && !i)return t.getElementsByClassName(n[1])
                }, n = null)
            }();
            n.contains = r.documentElement.contains ? function (n, t) {
                return n !== t && (n.contains ? n.contains(t) : !0)
            } : r.documentElement.compareDocumentPosition ? function (n, t) {
                return !!(n.compareDocumentPosition(t) & 16)
            } : function () {
                return !1
            };
            n.isXML = function (n) {
                var t = (n ? n.ownerDocument || n : 0).documentElement;
                return t ? t.nodeName !== "HTML" : !1
            };
            y = function (t, i) {
                for (var f, e = [], o = "", s = i.nodeType ? [i] : i, r, h; f = u.match.PSEUDO.exec(t);)o += f[0], t = t.replace(u.match.PSEUDO, "");
                for (t = u.relative[t] ? t + "*" : t, r = 0, h = s.length; r < h; r++)n(t, s[r], e);
                return n.filter(o, e)
            };
            i.find = n;
            i.expr = n.selectors;
            i.expr[":"] = i.expr.filters;
            i.unique = n.uniqueSort;
            i.text = n.getText;
            i.isXMLDoc = n.isXML;
            i.contains = n.contains
        }();
        var ou = /Until$/, su = /^(?:parents|prevUntil|prevAll)/, hu = /,/, cu = /^.[^:#\[\.,]*$/, lu = Array.prototype.slice, oi = i.expr.match.POS, au = {
            children: !0,
            contents: !0,
            next: !0,
            prev: !0
        };
        i.fn.extend({
            find: function (n) {
                var s = this, t, f, r, o, u, e;
                if (typeof n != "string")return i(n).filter(function () {
                    for (t = 0, f = s.length; t < f; t++)if (i.contains(s[t], this))return !0
                });
                for (r = this.pushStack("", "find", n), t = 0, f = this.length; t < f; t++)if (o = r.length, i.find(n, this[t], r), t > 0)for (u = o; u < r.length; u++)for (e = 0; e < o; e++)if (r[e] === r[u]) {
                    r.splice(u--, 1);
                    break
                }
                return r
            }, has: function (n) {
                var t = i(n);
                return this.filter(function () {
                    for (var n = 0, r = t.length; n < r; n++)if (i.contains(this, t[n]))return !0
                })
            }, not: function (n) {
                return this.pushStack(hi(this, n, !1), "not", n)
            }, filter: function (n) {
                return this.pushStack(hi(this, n, !0), "filter", n)
            }, is: function (n) {
                return !!n && (typeof n == "string" ? i.filter(n, this).length > 0 : this.filter(n).length > 0)
            }, closest: function (n, t) {
                var f = [], e, s, r = this[0], h, u, o, c, l;
                if (i.isArray(n)) {
                    if (o = {}, c = 1, r && n.length) {
                        for (e = 0, s = n.length; e < s; e++)u = n[e], o[u] || (o[u] = oi.test(u) ? i(u, t || this.context) : u);
                        while (r && r.ownerDocument && r !== t) {
                            for (u in o)h = o[u], (h.jquery ? h.index(r) > -1 : i(r).is(h)) && f.push({
                                selector: u,
                                elem: r,
                                level: c
                            });
                            r = r.parentNode;
                            c++
                        }
                    }
                    return f
                }
                for (l = oi.test(n) || typeof n != "string" ? i(n, t || this.context) : 0, e = 0, s = this.length; e < s; e++)for (r = this[e]; r;)if (l ? l.index(r) > -1 : i.find.matchesSelector(r, n)) {
                    f.push(r);
                    break
                } else if (r = r.parentNode, !r || !r.ownerDocument || r === t || r.nodeType === 11)break;
                return f = f.length > 1 ? i.unique(f) : f, this.pushStack(f, "closest", n)
            }, index: function (n) {
                return n ? typeof n == "string" ? i.inArray(this[0], i(n)) : i.inArray(n.jquery ? n[0] : n, this) : this[0] && this[0].parentNode ? this.prevAll().length : -1
            }, add: function (n, t) {
                var u = typeof n == "string" ? i(n, t) : i.makeArray(n && n.nodeType ? [n] : n), r = i.merge(this.get(), u);
                return this.pushStack(si(u[0]) || si(r[0]) ? r : i.unique(r))
            }, andSelf: function () {
                return this.add(this.prevObject)
            }
        });
        i.each({
            parent: function (n) {
                var t = n.parentNode;
                return t && t.nodeType !== 11 ? t : null
            }, parents: function (n) {
                return i.dir(n, "parentNode")
            }, parentsUntil: function (n, t, r) {
                return i.dir(n, "parentNode", r)
            }, next: function (n) {
                return i.nth(n, 2, "nextSibling")
            }, prev: function (n) {
                return i.nth(n, 2, "previousSibling")
            }, nextAll: function (n) {
                return i.dir(n, "nextSibling")
            }, prevAll: function (n) {
                return i.dir(n, "previousSibling")
            }, nextUntil: function (n, t, r) {
                return i.dir(n, "nextSibling", r)
            }, prevUntil: function (n, t, r) {
                return i.dir(n, "previousSibling", r)
            }, siblings: function (n) {
                return i.sibling(n.parentNode.firstChild, n)
            }, children: function (n) {
                return i.sibling(n.firstChild)
            }, contents: function (n) {
                return i.nodeName(n, "iframe") ? n.contentDocument || n.contentWindow.document : i.makeArray(n.childNodes)
            }
        }, function (n, t) {
            i.fn[n] = function (r, u) {
                var f = i.map(this, t, r), e = lu.call(arguments);
                return ou.test(n) || (u = r), u && typeof u == "string" && (f = i.filter(u, f)), f = this.length > 1 && !au[n] ? i.unique(f) : f, (this.length > 1 || hu.test(u)) && su.test(n) && (f = f.reverse()), this.pushStack(f, n, e.join(","))
            }
        });
        i.extend({
            filter: function (n, t, r) {
                return r && (n = ":not(" + n + ")"), t.length === 1 ? i.find.matchesSelector(t[0], n) ? [t[0]] : [] : i.find.matches(n, t)
            }, dir: function (n, r, u) {
                for (var e = [], f = n[r]; f && f.nodeType !== 9 && (u === t || f.nodeType !== 1 || !i(f).is(u));)f.nodeType === 1 && e.push(f), f = f[r];
                return e
            }, nth: function (n, t, i) {
                t = t || 1;
                for (var r = 0; n; n = n[i])if (n.nodeType === 1 && ++r === t)break;
                return n
            }, sibling: function (n, t) {
                for (var i = []; n; n = n.nextSibling)n.nodeType === 1 && n !== t && i.push(n);
                return i
            }
        });
        var vu = / jQuery\d+="(?:\d+|null)"/g, at = /^\s+/, ci = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig, li = /<([\w:]+)/, yu = /<tbody/i, pu = /<|&#?\w+;/, ai = /<(?:script|object|embed|option|style)/i, vi = /checked\s*(?:[^=]|=\s*.checked.)/i, wu = /\/(java|ecma)script/i, bu = /^\s*<!(?:\[CDATA\[|\-\-)/, u = {
            option: [1, "<select multiple='multiple'>", "<\/select>"],
            legend: [1, "<fieldset>", "<\/fieldset>"],
            thead: [1, "<table>", "<\/table>"],
            tr: [2, "<table><tbody>", "<\/tbody><\/table>"],
            td: [3, "<table><tbody><tr>", "<\/tr><\/tbody><\/table>"],
            col: [2, "<table><tbody><\/tbody><colgroup>", "<\/colgroup><\/table>"],
            area: [1, "<map>", "<\/map>"],
            _default: [0, "", ""]
        };
        u.optgroup = u.option;
        u.tbody = u.tfoot = u.colgroup = u.caption = u.thead;
        u.th = u.td;
        i.support.htmlSerialize || (u._default = [1, "div<div>", "<\/div>"]);
        i.fn.extend({
            text: function (n) {
                return i.isFunction(n) ? this.each(function (t) {
                    var r = i(this);
                    r.text(n.call(this, t, r.text()))
                }) : typeof n != "object" && n !== t ? this.empty().append((this[0] && this[0].ownerDocument || r).createTextNode(n)) : i.text(this)
            }, wrapAll: function (n) {
                if (i.isFunction(n))return this.each(function (t) {
                    i(this).wrapAll(n.call(this, t))
                });
                if (this[0]) {
                    var t = i(n, this[0].ownerDocument).eq(0).clone(!0);
                    this[0].parentNode && t.insertBefore(this[0]);
                    t.map(function () {
                        for (var n = this; n.firstChild && n.firstChild.nodeType === 1;)n = n.firstChild;
                        return n
                    }).append(this)
                }
                return this
            }, wrapInner: function (n) {
                return i.isFunction(n) ? this.each(function (t) {
                    i(this).wrapInner(n.call(this, t))
                }) : this.each(function () {
                    var t = i(this), r = t.contents();
                    r.length ? r.wrapAll(n) : t.append(n)
                })
            }, wrap: function (n) {
                return this.each(function () {
                    i(this).wrapAll(n)
                })
            }, unwrap: function () {
                return this.parent().each(function () {
                    i.nodeName(this, "body") || i(this).replaceWith(this.childNodes)
                }).end()
            }, append: function () {
                return this.domManip(arguments, !0, function (n) {
                    this.nodeType === 1 && this.appendChild(n)
                })
            }, prepend: function () {
                return this.domManip(arguments, !0, function (n) {
                    this.nodeType === 1 && this.insertBefore(n, this.firstChild)
                })
            }, before: function () {
                if (this[0] && this[0].parentNode)return this.domManip(arguments, !1, function (n) {
                    this.parentNode.insertBefore(n, this)
                });
                if (arguments.length) {
                    var n = i(arguments[0]);
                    return n.push.apply(n, this.toArray()), this.pushStack(n, "before", arguments)
                }
            }, after: function () {
                if (this[0] && this[0].parentNode)return this.domManip(arguments, !1, function (n) {
                    this.parentNode.insertBefore(n, this.nextSibling)
                });
                if (arguments.length) {
                    var n = this.pushStack(this, "after", arguments);
                    return n.push.apply(n, i(arguments[0]).toArray()), n
                }
            }, remove: function (n, t) {
                for (var u = 0, r; (r = this[u]) != null; u++)(!n || i.filter(n, [r]).length) && (t || r.nodeType !== 1 || (i.cleanData(r.getElementsByTagName("*")), i.cleanData([r])), r.parentNode && r.parentNode.removeChild(r));
                return this
            }, empty: function () {
                for (var t = 0, n; (n = this[t]) != null; t++)for (n.nodeType === 1 && i.cleanData(n.getElementsByTagName("*")); n.firstChild;)n.removeChild(n.firstChild);
                return this
            }, clone: function (n, t) {
                return n = n == null ? !1 : n, t = t == null ? n : t, this.map(function () {
                    return i.clone(this, n, t)
                })
            }, html: function (n) {
                if (n === t)return this[0] && this[0].nodeType === 1 ? this[0].innerHTML.replace(vu, "") : null;
                if (typeof n != "string" || ai.test(n) || !i.support.leadingWhitespace && at.test(n) || u[(li.exec(n) || ["", ""])[1].toLowerCase()])i.isFunction(n) ? this.each(function (t) {
                    var r = i(this);
                    r.html(n.call(this, t, r.html()))
                }) : this.empty().append(n); else {
                    n = n.replace(ci, "<$1><\/$2>");
                    try {
                        for (var r = 0, f = this.length; r < f; r++)this[r].nodeType === 1 && (i.cleanData(this[r].getElementsByTagName("*")), this[r].innerHTML = n)
                    } catch (e) {
                        this.empty().append(n)
                    }
                }
                return this
            }, replaceWith: function (n) {
                return this[0] && this[0].parentNode ? i.isFunction(n) ? this.each(function (t) {
                    var r = i(this), u = r.html();
                    r.replaceWith(n.call(this, t, u))
                }) : (typeof n != "string" && (n = i(n).detach()), this.each(function () {
                    var t = this.nextSibling, r = this.parentNode;
                    i(this).remove();
                    t ? i(t).before(n) : i(r).append(n)
                })) : this.length ? this.pushStack(i(i.isFunction(n) ? n() : n), "replaceWith", n) : this
            }, detach: function (n) {
                return this.remove(n, !0)
            }, domManip: function (n, r, u) {
                var c, h, f, o, e = n[0], l = [];
                if (!i.support.checkClone && arguments.length === 3 && typeof e == "string" && vi.test(e))return this.each(function () {
                    i(this).domManip(n, r, u, !0)
                });
                if (i.isFunction(e))return this.each(function (f) {
                    var o = i(this);
                    n[0] = e.call(this, f, r ? o.html() : t);
                    o.domManip(n, r, u)
                });
                if (this[0]) {
                    if (o = e && e.parentNode, c = i.support.parentNode && o && o.nodeType === 11 && o.childNodes.length === this.length ? {fragment: o} : i.buildFragment(n, this, l), f = c.fragment, h = f.childNodes.length === 1 ? f = f.firstChild : f.firstChild, h) {
                        r = r && i.nodeName(h, "tr");
                        for (var s = 0, a = this.length, v = a - 1; s < a; s++)u.call(r ? ku(this[s], h) : this[s], c.cacheable || a > 1 && s < v ? i.clone(f, !0, !0) : f)
                    }
                    l.length && i.each(l, du)
                }
                return this
            }
        });
        i.buildFragment = function (n, t, u) {
            var f, s, o, e;
            return t && t[0] && (e = t[0].ownerDocument || t[0]), e.createDocumentFragment || (e = r), n.length === 1 && typeof n[0] == "string" && n[0].length < 512 && e === r && n[0].charAt(0) === "<" && !ai.test(n[0]) && (i.support.checkClone || !vi.test(n[0])) && (s = !0, o = i.fragments[n[0]], o && o !== 1 && (f = o)), f || (f = e.createDocumentFragment(), i.clean(n, e, f, u)), s && (i.fragments[n[0]] = o ? f : 1), {
                fragment: f,
                cacheable: s
            }
        };
        i.fragments = {};
        i.each({
            appendTo: "append",
            prependTo: "prepend",
            insertBefore: "before",
            insertAfter: "after",
            replaceAll: "replaceWith"
        }, function (n, t) {
            i.fn[n] = function (r) {
                var e = [], u = i(r), o = this.length === 1 && this[0].parentNode, f, h, s;
                if (o && o.nodeType === 11 && o.childNodes.length === 1 && u.length === 1)return u[t](this[0]), this;
                for (f = 0, h = u.length; f < h; f++)s = (f > 0 ? this.clone(!0) : this).get(), i(u[f])[t](s), e = e.concat(s);
                return this.pushStack(e, n, u.selector)
            }
        });
        i.extend({
            clone: function (n, t, r) {
                var o = n.cloneNode(!0), f, e, u;
                if ((!i.support.noCloneEvent || !i.support.noCloneChecked) && (n.nodeType === 1 || n.nodeType === 11) && !i.isXMLDoc(n))for (pi(n, o), f = g(n), e = g(o), u = 0; f[u]; ++u)e[u] && pi(f[u], e[u]);
                if (t && (yi(n, o), r))for (f = g(n), e = g(o), u = 0; f[u]; ++u)yi(f[u], e[u]);
                return f = e = null, o
            }, clean: function (n, t, f, e) {
                var p, s, c, h, o, y, a, b, k;
                for (t = t || r, typeof t.createElement == "undefined" && (t = t.ownerDocument || t[0] && t[0].ownerDocument || r), s = [], h = 0; (o = n[h]) != null; h++)if (typeof o == "number" && (o += ""), o) {
                    if (typeof o == "string")if (pu.test(o)) {
                        o = o.replace(ci, "<$1><\/$2>");
                        var w = (li.exec(o) || ["", ""])[1].toLowerCase(), v = u[w] || u._default, d = v[0], l = t.createElement("div");
                        for (l.innerHTML = v[1] + o + v[2]; d--;)l = l.lastChild;
                        if (!i.support.tbody)for (y = yu.test(o), a = w === "table" && !y ? l.firstChild && l.firstChild.childNodes : v[1] === "<table>" && !y ? l.childNodes : [], c = a.length - 1; c >= 0; --c)i.nodeName(a[c], "tbody") && !a[c].childNodes.length && a[c].parentNode.removeChild(a[c]);
                        !i.support.leadingWhitespace && at.test(o) && l.insertBefore(t.createTextNode(at.exec(o)[0]), l.firstChild);
                        o = l.childNodes
                    } else o = t.createTextNode(o);
                    if (!i.support.appendChecked)if (o[0] && typeof(b = o.length) == "number")for (c = 0; c < b; c++)bi(o[c]); else bi(o);
                    o.nodeType ? s.push(o) : s = i.merge(s, o)
                }
                if (f)for (p = function (n) {
                    return !n.type || wu.test(n.type)
                }, h = 0; s[h]; h++)e && i.nodeName(s[h], "script") && (!s[h].type || s[h].type.toLowerCase() === "text/javascript") ? e.push(s[h].parentNode ? s[h].parentNode.removeChild(s[h]) : s[h]) : (s[h].nodeType === 1 && (k = i.grep(s[h].getElementsByTagName("script"), p), s.splice.apply(s, [h + 1, 0].concat(k))), f.appendChild(s[h]));
                return s
            }, cleanData: function (n) {
                for (var r, u, e = i.cache, s = i.expando, h = i.event.special, c = i.support.deleteExpando, t, f, o = 0; (t = n[o]) != null; o++)if ((!t.nodeName || !i.noData[t.nodeName.toLowerCase()]) && (u = t[i.expando], u)) {
                    if (r = e[u] && e[u][s], r && r.events) {
                        for (f in r.events)h[f] ? i.event.remove(t, f) : i.removeEvent(t, f, r.handle);
                        r.handle && (r.handle.elem = null)
                    }
                    c ? delete t[i.expando] : t.removeAttribute && t.removeAttribute(i.expando);
                    delete e[u]
                }
            }
        });
        var vt = /alpha\([^)]*\)/i, gu = /opacity=([^)]*)/, nf = /([A-Z]|^ms)/g, ki = /^-?\d+(?:px)?$/i, tf = /^-?\d/, rf = /^([\-+])=([\-+.\de]+)/, uf = {
            position: "absolute",
            visibility: "hidden",
            display: "block"
        }, ff = ["Left", "Right"], ef = ["Top", "Bottom"], c, di, gi;
        i.fn.css = function (n, r) {
            return arguments.length === 2 && r === t ? this : i.access(this, n, r, !0, function (n, r, u) {
                return u !== t ? i.style(n, r, u) : i.css(n, r)
            })
        };
        i.extend({
            cssHooks: {
                opacity: {
                    get: function (n, t) {
                        if (t) {
                            var i = c(n, "opacity", "opacity");
                            return i === "" ? "1" : i
                        }
                        return n.style.opacity
                    }
                }
            },
            cssNumber: {
                fillOpacity: !0,
                fontWeight: !0,
                lineHeight: !0,
                opacity: !0,
                orphans: !0,
                widows: !0,
                zIndex: !0,
                zoom: !0
            },
            cssProps: {float: i.support.cssFloat ? "cssFloat" : "styleFloat"},
            style: function (n, r, u, f) {
                if (n && n.nodeType !== 3 && n.nodeType !== 8 && n.style) {
                    var o, s, h = i.camelCase(r), c = n.style, e = i.cssHooks[h];
                    if (r = i.cssProps[h] || h, u !== t) {
                        if (s = typeof u, s === "string" && (o = rf.exec(u)) && (u = +(o[1] + 1) * +o[2] + parseFloat(i.css(n, r)), s = "number"), u == null || s === "number" && isNaN(u))return;
                        if (s !== "number" || i.cssNumber[h] || (u += "px"), !e || !("set"in e) || (u = e.set(n, u)) !== t)try {
                            c[r] = u
                        } catch (l) {
                        }
                    } else return e && "get"in e && (o = e.get(n, !1, f)) !== t ? o : c[r]
                }
            },
            css: function (n, r, u) {
                var e, f;
                return (r = i.camelCase(r), f = i.cssHooks[r], r = i.cssProps[r] || r, r === "cssFloat" && (r = "float"), f && "get"in f && (e = f.get(n, !0, u)) !== t) ? e : c ? c(n, r) : void 0
            },
            swap: function (n, t, i) {
                var u = {}, r;
                for (r in t)u[r] = n.style[r], n.style[r] = t[r];
                i.call(n);
                for (r in t)n.style[r] = u[r]
            }
        });
        i.curCSS = i.css;
        i.each(["height", "width"], function (n, t) {
            i.cssHooks[t] = {
                get: function (n, r, u) {
                    var f;
                    if (r)return n.offsetWidth !== 0 ? nr(n, t, u) : (i.swap(n, uf, function () {
                        f = nr(n, t, u)
                    }), f)
                }, set: function (n, t) {
                    if (ki.test(t)) {
                        if (t = parseFloat(t), t >= 0)return t + "px"
                    } else return t
                }
            }
        });
        i.support.opacity || (i.cssHooks.opacity = {
            get: function (n, t) {
                return gu.test((t && n.currentStyle ? n.currentStyle.filter : n.style.filter) || "") ? parseFloat(RegExp.$1) / 100 + "" : t ? "1" : ""
            }, set: function (n, t) {
                var r = n.style, u = n.currentStyle, e = i.isNaN(t) ? "" : "alpha(opacity=" + t * 100 + ")", f = u && u.filter || r.filter || "";
                (r.zoom = 1, t >= 1 && i.trim(f.replace(vt, "")) === "" && (r.removeAttribute("filter"), u && !u.filter)) || (r.filter = vt.test(f) ? f.replace(vt, e) : f + " " + e)
            }
        });
        i(function () {
            i.support.reliableMarginRight || (i.cssHooks.marginRight = {
                get: function (n, t) {
                    var r;
                    return i.swap(n, {display: "inline-block"}, function () {
                        r = t ? c(n, "margin-right", "marginRight") : n.style.marginRight
                    }), r
                }
            })
        });
        r.defaultView && r.defaultView.getComputedStyle && (di = function (n, r) {
            var u, f, e;
            return (r = r.replace(nf, "-$1").toLowerCase(), !(f = n.ownerDocument.defaultView)) ? t : ((e = f.getComputedStyle(n, null)) && (u = e.getPropertyValue(r), u !== "" || i.contains(n.ownerDocument.documentElement, n) || (u = i.style(n, r))), u)
        });
        r.documentElement.currentStyle && (gi = function (n, t) {
            var f, i = n.currentStyle && n.currentStyle[t], u = n.runtimeStyle && n.runtimeStyle[t], r = n.style;
            return !ki.test(i) && tf.test(i) && (f = r.left, u && (n.runtimeStyle.left = n.currentStyle.left), r.left = t === "fontSize" ? "1em" : i || 0, i = r.pixelLeft + "px", r.left = f, u && (n.runtimeStyle.left = u)), i === "" ? "auto" : i
        });
        c = di || gi;
        i.expr && i.expr.filters && (i.expr.filters.hidden = function (n) {
            var t = n.offsetWidth, r = n.offsetHeight;
            return t === 0 && r === 0 || !i.support.reliableHiddenOffsets && (n.style.display || i.css(n, "display")) === "none"
        }, i.expr.filters.visible = function (n) {
            return !i.expr.filters.hidden(n)
        });
        var of = /%20/g, sf = /\[\]$/, tr = /\r?\n/g, hf = /#.*$/, cf = /^(.*?):[ \t]*([^\r\n]*)\r?$/mg, lf = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i, af = /^(?:GET|HEAD)$/, vf = /^\/\//, ir = /\?/, yf = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, pf = /^(?:select|textarea)/i, rr = /\s+/, wf = /([?&])_=[^&]*/, ur = /^([\w\+\.\-]+:)(?:\/\/([^\/?#:]*)(?::(\d+))?)?/, fr = i.fn.load, yt = {}, er = {}, s, h, or = ["*/"] + ["*"];
        try {
            s = kr.href
        } catch (ie) {
            s = r.createElement("a");
            s.href = "";
            s = s.href
        }
        h = ur.exec(s.toLowerCase()) || [];
        i.fn.extend({
            load: function (n, r, u) {
                var f, e, o, s;
                return typeof n != "string" && fr ? fr.apply(this, arguments) : this.length ? (f = n.indexOf(" "), f >= 0 && (e = n.slice(f, n.length), n = n.slice(0, f)), o = "GET", r && (i.isFunction(r) ? (u = r, r = t) : typeof r == "object" && (r = i.param(r, i.ajaxSettings.traditional), o = "POST")), s = this, i.ajax({
                    url: n,
                    type: o,
                    dataType: "html",
                    data: r,
                    complete: function (n, t, r) {
                        r = n.responseText;
                        n.isResolved() && (n.done(function (n) {
                            r = n
                        }), s.html(e ? i("<div>").append(r.replace(yf, "")).find(e) : r));
                        u && s.each(u, [r, t, n])
                    }
                }), this) : this
            }, serialize: function () {
                return i.param(this.serializeArray())
            }, serializeArray: function () {
                return this.map(function () {
                    return this.elements ? i.makeArray(this.elements) : this
                }).filter(function () {
                    return this.name && !this.disabled && (this.checked || pf.test(this.nodeName) || lf.test(this.type))
                }).map(function (n, t) {
                    var r = i(this).val();
                    return r == null ? null : i.isArray(r) ? i.map(r, function (n) {
                        return {name: t.name, value: n.replace(tr, "\r\n")}
                    }) : {name: t.name, value: r.replace(tr, "\r\n")}
                }).get()
            }
        });
        i.each("ajaxStart ajaxStop ajaxComplete ajaxError ajaxSuccess ajaxSend".split(" "), function (n, t) {
            i.fn[t] = function (n) {
                return this.bind(t, n)
            }
        });
        i.each(["get", "post"], function (n, r) {
            i[r] = function (n, u, f, e) {
                return i.isFunction(u) && (e = e || f, f = u, u = t), i.ajax({
                    type: r,
                    url: n,
                    data: u,
                    success: f,
                    dataType: e
                })
            }
        });
        i.extend({
            getScript: function (n, r) {
                return i.get(n, t, r, "script")
            },
            getJSON: function (n, t, r) {
                return i.get(n, t, r, "json")
            },
            ajaxSetup: function (n, t) {
                return t ? hr(n, i.ajaxSettings) : (t = n, n = i.ajaxSettings), hr(n, t), n
            },
            ajaxSettings: {
                url: s,
                isLocal: /^(?:about|app|app\-storage|.+\-extension|file|res|widget):$/.test(h[1]),
                global: !0,
                type: "GET",
                contentType: "application/x-www-form-urlencoded",
                processData: !0,
                async: !0,
                accepts: {
                    xml: "application/xml, text/xml",
                    html: "text/html",
                    text: "text/plain",
                    json: "application/json, text/javascript",
                    "*": or
                },
                contents: {xml: /xml/, html: /html/, json: /json/},
                responseFields: {xml: "responseXML", text: "responseText"},
                converters: {"* text": n.String, "text html": !0, "text json": i.parseJSON, "text xml": i.parseXML},
                flatOptions: {context: !0, url: !0}
            },
            ajaxPrefilter: sr(yt),
            ajaxTransport: sr(er),
            ajax: function (n, r) {
                function w(n, r, h, l) {
                    if (e !== 2) {
                        e = 2;
                        g && clearTimeout(g);
                        c = t;
                        d = l || "";
                        f.readyState = n > 0 ? 4 : 0;
                        var p, nt, w, a = r, ut = h ? bf(u, f, h) : t, tt, it;
                        if (n >= 200 && n < 300 || n === 304)if (u.ifModified && ((tt = f.getResponseHeader("Last-Modified")) && (i.lastModified[o] = tt), (it = f.getResponseHeader("Etag")) && (i.etag[o] = it)), n === 304)a = "notmodified", p = !0; else try {
                            nt = kf(u, ut);
                            a = "success";
                            p = !0
                        } catch (ft) {
                            a = "parsererror";
                            w = ft
                        } else w = a, (!a || n) && (a = "error", n < 0 && (n = 0));
                        f.status = n;
                        f.statusText = "" + (r || a);
                        p ? k.resolveWith(s, [nt, a, f]) : k.rejectWith(s, [f, a, w]);
                        f.statusCode(y);
                        y = t;
                        v && b.trigger("ajax" + (p ? "Success" : "Error"), [f, u, p ? nt : w]);
                        rt.resolveWith(s, [f, a]);
                        v && (b.trigger("ajaxComplete", [f, u]), --i.active || i.event.trigger("ajaxStop"))
                    }
                }

                var tt, it;
                typeof n == "object" && (r = n, n = t);
                r = r || {};
                var u = i.ajaxSetup({}, r), s = u.context || u, b = s !== u && (s.nodeType || s instanceof i) ? i(s) : i.event, k = i.Deferred(), rt = i._Deferred(), y = u.statusCode || {}, o, ut = {}, ft = {}, d, p, c, g, l, e = 0, v, a, f = {
                    readyState: 0,
                    setRequestHeader: function (n, t) {
                        if (!e) {
                            var i = n.toLowerCase();
                            n = ft[i] = ft[i] || n;
                            ut[n] = t
                        }
                        return this
                    },
                    getAllResponseHeaders: function () {
                        return e === 2 ? d : null
                    },
                    getResponseHeader: function (n) {
                        var i;
                        if (e === 2) {
                            if (!p)for (p = {}; i = cf.exec(d);)p[i[1].toLowerCase()] = i[2];
                            i = p[n.toLowerCase()]
                        }
                        return i === t ? null : i
                    },
                    overrideMimeType: function (n) {
                        return e || (u.mimeType = n), this
                    },
                    abort: function (n) {
                        return n = n || "abort", c && c.abort(n), w(0, n), this
                    }
                };
                if (k.promise(f), f.success = f.done, f.error = f.fail, f.complete = rt.done, f.statusCode = function (n) {
                        if (n) {
                            var t;
                            if (e < 2)for (t in n)y[t] = [y[t], n[t]]; else t = n[f.status], f.then(t, t)
                        }
                        return this
                    }, u.url = ((n || u.url) + "").replace(hf, "").replace(vf, h[1] + "//"), u.dataTypes = i.trim(u.dataType || "*").toLowerCase().split(rr), u.crossDomain == null && (l = ur.exec(u.url.toLowerCase()), u.crossDomain = !!(l && (l[1] != h[1] || l[2] != h[2] || (l[3] || (l[1] === "http:" ? 80 : 443)) != (h[3] || (h[1] === "http:" ? 80 : 443))))), u.data && u.processData && typeof u.data != "string" && (u.data = i.param(u.data, u.traditional)), nt(yt, u, r, f), e === 2)return !1;
                v = u.global;
                u.type = u.type.toUpperCase();
                u.hasContent = !af.test(u.type);
                v && i.active++ == 0 && i.event.trigger("ajaxStart");
                u.hasContent || (u.data && (u.url += (ir.test(u.url) ? "&" : "?") + u.data, delete u.data), o = u.url, u.cache === !1 && (tt = i.now(), it = u.url.replace(wf, "$1_=" + tt), u.url = it + (it === u.url ? (ir.test(u.url) ? "&" : "?") + "_=" + tt : "")));
                (u.data && u.hasContent && u.contentType !== !1 || r.contentType) && f.setRequestHeader("Content-Type", u.contentType);
                u.ifModified && (o = o || u.url, i.lastModified[o] && f.setRequestHeader("If-Modified-Since", i.lastModified[o]), i.etag[o] && f.setRequestHeader("If-None-Match", i.etag[o]));
                f.setRequestHeader("Accept", u.dataTypes[0] && u.accepts[u.dataTypes[0]] ? u.accepts[u.dataTypes[0]] + (u.dataTypes[0] !== "*" ? ", " + or + "; q=0.01" : "") : u.accepts["*"]);
                for (a in u.headers)f.setRequestHeader(a, u.headers[a]);
                if (u.beforeSend && (u.beforeSend.call(s, f, u) === !1 || e === 2))return f.abort(), !1;
                for (a in{success: 1, error: 1, complete: 1})f[a](u[a]);
                if (c = nt(er, u, r, f), c) {
                    f.readyState = 1;
                    v && b.trigger("ajaxSend", [f, u]);
                    u.async && u.timeout > 0 && (g = setTimeout(function () {
                        f.abort("timeout")
                    }, u.timeout));
                    try {
                        e = 1;
                        c.send(ut, w)
                    } catch (et) {
                        e < 2 ? w(-1, et) : i.error(et)
                    }
                } else w(-1, "No Transport");
                return f
            },
            param: function (n, r) {
                var u = [], e = function (n, t) {
                    t = i.isFunction(t) ? t() : t;
                    u[u.length] = encodeURIComponent(n) + "=" + encodeURIComponent(t)
                }, f;
                if (r === t && (r = i.ajaxSettings.traditional), i.isArray(n) || n.jquery && !i.isPlainObject(n))i.each(n, function () {
                    e(this.name, this.value)
                }); else for (f in n)pt(f, n[f], r, e);
                return u.join("&").replace(of, "+")
            }
        });
        i.extend({active: 0, lastModified: {}, etag: {}});
        cr = i.now();
        w = /(\=)\?(&|$)|\?\?/i;
        i.ajaxSetup({
            jsonp: "callback", jsonpCallback: function () {
                return i.expando + "_" + cr++
            }
        });
        i.ajaxPrefilter("json jsonp", function (t, r, u) {
            var h = t.contentType === "application/x-www-form-urlencoded" && typeof t.data == "string";
            if (t.dataTypes[0] === "jsonp" || t.jsonp !== !1 && (w.test(t.url) || h && w.test(t.data))) {
                var o, f = t.jsonpCallback = i.isFunction(t.jsonpCallback) ? t.jsonpCallback() : t.jsonpCallback, c = n[f], e = t.url, s = t.data, l = "$1" + f + "$2";
                return t.jsonp !== !1 && (e = e.replace(w, l), t.url === e && (h && (s = s.replace(w, l)), t.data === s && (e += (/\?/.test(e) ? "&" : "?") + t.jsonp + "=" + f))), t.url = e, t.data = s, n[f] = function (n) {
                    o = [n]
                }, u.always(function () {
                    n[f] = c;
                    o && i.isFunction(c) && n[f](o[0])
                }), t.converters["script json"] = function () {
                    return o || i.error(f + " was not called"), o[0]
                }, t.dataTypes[0] = "json", "script"
            }
        });
        i.ajaxSetup({
            accepts: {script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"},
            contents: {script: /javascript|ecmascript/},
            converters: {
                "text script": function (n) {
                    return i.globalEval(n), n
                }
            }
        });
        i.ajaxPrefilter("script", function (n) {
            n.cache === t && (n.cache = !1);
            n.crossDomain && (n.type = "GET", n.global = !1)
        });
        i.ajaxTransport("script", function (n) {
            if (n.crossDomain) {
                var i, u = r.head || r.getElementsByTagName("head")[0] || r.documentElement;
                return {
                    send: function (f, e) {
                        i = r.createElement("script");
                        i.async = "async";
                        n.scriptCharset && (i.charset = n.scriptCharset);
                        i.src = n.url;
                        i.onload = i.onreadystatechange = function (n, r) {
                            (r || !i.readyState || /loaded|complete/.test(i.readyState)) && (i.onload = i.onreadystatechange = null, u && i.parentNode && u.removeChild(i), i = t, r || e(200, "success"))
                        };
                        u.insertBefore(i, u.firstChild)
                    }, abort: function () {
                        if (i)i.onload(0, 1)
                    }
                }
            }
        });
        tt = n.ActiveXObject ? function () {
            for (var n in l)l[n](0, 1)
        } : !1;
        lr = 0;
        i.ajaxSettings.xhr = n.ActiveXObject ? function () {
            return !this.isLocal && ar() || df()
        } : ar, function (n) {
            i.extend(i.support, {ajax: !!n, cors: !!n && "withCredentials"in n})
        }(i.ajaxSettings.xhr());
        i.support.ajax && i.ajaxTransport(function (r) {
            if (!r.crossDomain || i.support.cors) {
                var u;
                return {
                    send: function (f, e) {
                        var o = r.xhr(), h, s;
                        if (r.username ? o.open(r.type, r.url, r.async, r.username, r.password) : o.open(r.type, r.url, r.async), r.xhrFields)for (s in r.xhrFields)o[s] = r.xhrFields[s];
                        r.mimeType && o.overrideMimeType && o.overrideMimeType(r.mimeType);
                        r.crossDomain || f["X-Requested-With"] || (f["X-Requested-With"] = "XMLHttpRequest");
                        try {
                            for (s in f)o.setRequestHeader(s, f[s])
                        } catch (c) {
                        }
                        o.send(r.hasContent && r.data || null);
                        u = function (n, f) {
                            var s, v, y, c, a;
                            try {
                                if (u && (f || o.readyState === 4))if (u = t, h && (o.onreadystatechange = i.noop, tt && delete l[h]), f)o.readyState !== 4 && o.abort(); else {
                                    s = o.status;
                                    y = o.getAllResponseHeaders();
                                    c = {};
                                    a = o.responseXML;
                                    a && a.documentElement && (c.xml = a);
                                    c.text = o.responseText;
                                    try {
                                        v = o.statusText
                                    } catch (w) {
                                        v = ""
                                    }
                                    s || !r.isLocal || r.crossDomain ? s === 1223 && (s = 204) : s = c.text ? 200 : 404
                                }
                            } catch (p) {
                                f || e(-1, p)
                            }
                            c && e(s, v, c, y)
                        };
                        r.async && o.readyState !== 4 ? (h = ++lr, tt && (l || (l = {}, i(n).unload(tt)), l[h] = u), o.onreadystatechange = u) : u()
                    }, abort: function () {
                        u && u(0, 1)
                    }
                }
            }
        });
        var wt = {}, f, a, gf = /^(?:toggle|show|hide)$/, ne = /^([+\-]=)?([\d+.\-]+)([a-z%]*)$/i, it, vr = [["height", "marginTop", "marginBottom", "paddingTop", "paddingBottom"], ["width", "marginLeft", "marginRight", "paddingLeft", "paddingRight"], ["opacity"]], rt;
        i.fn.extend({
            show: function (n, t, r) {
                var u, e, f, o;
                if (n || n === 0)return this.animate(v("show", 3), n, t, r);
                for (f = 0, o = this.length; f < o; f++)u = this[f], u.style && (e = u.style.display, i._data(u, "olddisplay") || e !== "none" || (e = u.style.display = ""), e === "" && i.css(u, "display") === "none" && i._data(u, "olddisplay", pr(u.nodeName)));
                for (f = 0; f < o; f++)u = this[f], u.style && (e = u.style.display, (e === "" || e === "none") && (u.style.display = i._data(u, "olddisplay") || ""));
                return this
            }, hide: function (n, t, r) {
                var u, f, e;
                if (n || n === 0)return this.animate(v("hide", 3), n, t, r);
                for (u = 0, f = this.length; u < f; u++)this[u].style && (e = i.css(this[u], "display"), e === "none" || i._data(this[u], "olddisplay") || i._data(this[u], "olddisplay", e));
                for (u = 0; u < f; u++)this[u].style && (this[u].style.display = "none");
                return this
            }, _toggle: i.fn.toggle, toggle: function (n, t, r) {
                var u = typeof n == "boolean";
                return i.isFunction(n) && i.isFunction(t) ? this._toggle.apply(this, arguments) : n == null || u ? this.each(function () {
                    var t = u ? n : i(this).is(":hidden");
                    i(this)[t ? "show" : "hide"]()
                }) : this.animate(v("toggle", 3), n, t, r), this
            }, fadeTo: function (n, t, i, r) {
                return this.filter(":hidden").css("opacity", 0).show().end().animate({opacity: t}, n, i, r)
            }, animate: function (n, t, r, u) {
                var f = i.speed(t, r, u);
                return i.isEmptyObject(n) ? this.each(f.complete, [!1]) : (n = i.extend({}, n), this[f.queue === !1 ? "each" : "queue"](function () {
                    f.queue === !1 && i._mark(this);
                    var u = i.extend({}, f), v = this.nodeType === 1, a = v && i(this).is(":hidden"), e, t, r, y, s, h, o, c, l;
                    u.animatedProperties = {};
                    for (r in n) {
                        if (e = i.camelCase(r), r !== e && (n[e] = n[r], delete n[r]), t = n[e], i.isArray(t) ? (u.animatedProperties[e] = t[1], t = n[e] = t[0]) : u.animatedProperties[e] = u.specialEasing && u.specialEasing[e] || u.easing || "swing", t === "hide" && a || t === "show" && !a)return u.complete.call(this);
                        v && (e === "height" || e === "width") && (u.overflow = [this.style.overflow, this.style.overflowX, this.style.overflowY], i.css(this, "display") === "inline" && i.css(this, "float") === "none" && (i.support.inlineBlockNeedsLayout ? (y = pr(this.nodeName), y === "inline" ? this.style.display = "inline-block" : (this.style.display = "inline", this.style.zoom = 1)) : this.style.display = "inline-block"))
                    }
                    u.overflow != null && (this.style.overflow = "hidden");
                    for (r in n)s = new i.fx(this, u, r), t = n[r], gf.test(t) ? s[t === "toggle" ? a ? "show" : "hide" : t]() : (h = ne.exec(t), o = s.cur(), h ? (c = parseFloat(h[2]), l = h[3] || (i.cssNumber[r] ? "" : "px"), l !== "px" && (i.style(this, r, (c || 1) + l), o = (c || 1) / s.cur() * o, i.style(this, r, o + l)), h[1] && (c = (h[1] === "-=" ? -1 : 1) * c + o), s.custom(o, c, l)) : s.custom(o, t, ""));
                    return !0
                }))
            }, stop: function (n, t) {
                return n && this.queue([]), this.each(function () {
                    var n = i.timers, r = n.length;
                    for (t || i._unmark(!0, this); r--;)n[r].elem === this && (t && n[r](!0), n.splice(r, 1))
                }), t || this.dequeue(), this
            }
        });
        i.each({
            slideDown: v("show", 1),
            slideUp: v("hide", 1),
            slideToggle: v("toggle", 1),
            fadeIn: {opacity: "show"},
            fadeOut: {opacity: "hide"},
            fadeToggle: {opacity: "toggle"}
        }, function (n, t) {
            i.fn[n] = function (n, i, r) {
                return this.animate(t, n, i, r)
            }
        });
        i.extend({
            speed: function (n, t, r) {
                var u = n && typeof n == "object" ? i.extend({}, n) : {
                    complete: r || !r && t || i.isFunction(n) && n,
                    duration: n,
                    easing: r && t || t && !i.isFunction(t) && t
                };
                return u.duration = i.fx.off ? 0 : typeof u.duration == "number" ? u.duration : u.duration in i.fx.speeds ? i.fx.speeds[u.duration] : i.fx.speeds._default, u.old = u.complete, u.complete = function (n) {
                    i.isFunction(u.old) && u.old.call(this);
                    u.queue !== !1 ? i.dequeue(this) : n !== !1 && i._unmark(this)
                }, u
            }, easing: {
                linear: function (n, t, i, r) {
                    return i + r * n
                }, swing: function (n, t, i, r) {
                    return (-Math.cos(n * Math.PI) / 2 + .5) * r + i
                }
            }, timers: [], fx: function (n, t, i) {
                this.options = t;
                this.elem = n;
                this.prop = i;
                t.orig = t.orig || {}
            }
        });
        i.fx.prototype = {
            update: function () {
                this.options.step && this.options.step.call(this.elem, this.now, this);
                (i.fx.step[this.prop] || i.fx.step._default)(this)
            }, cur: function () {
                if (this.elem[this.prop] != null && (!this.elem.style || this.elem.style[this.prop] == null))return this.elem[this.prop];
                var t, n = i.css(this.elem, this.prop);
                return isNaN(t = parseFloat(n)) ? !n || n === "auto" ? 0 : n : t
            }, custom: function (n, t, r) {
                function u(n) {
                    return e.step(n)
                }

                var e = this, f = i.fx;
                this.startTime = rt || yr();
                this.start = n;
                this.end = t;
                this.unit = r || this.unit || (i.cssNumber[this.prop] ? "" : "px");
                this.now = this.start;
                this.pos = this.state = 0;
                u.elem = this.elem;
                u() && i.timers.push(u) && !it && (it = setInterval(f.tick, f.interval))
            }, show: function () {
                this.options.orig[this.prop] = i.style(this.elem, this.prop);
                this.options.show = !0;
                this.custom(this.prop === "width" || this.prop === "height" ? 1 : 0, this.cur());
                i(this.elem).show()
            }, hide: function () {
                this.options.orig[this.prop] = i.style(this.elem, this.prop);
                this.options.hide = !0;
                this.custom(this.cur(), 0)
            }, step: function (n) {
                var u = rt || yr(), o = !0, r = this.elem, t = this.options, s, f, e;
                if (n || u >= t.duration + this.startTime) {
                    this.now = this.end;
                    this.pos = this.state = 1;
                    this.update();
                    t.animatedProperties[this.prop] = !0;
                    for (s in t.animatedProperties)t.animatedProperties[s] !== !0 && (o = !1);
                    if (o) {
                        if (t.overflow == null || i.support.shrinkWrapBlocks || i.each(["", "X", "Y"], function (n, i) {
                                r.style["overflow" + i] = t.overflow[n]
                            }), t.hide && i(r).hide(), t.hide || t.show)for (e in t.animatedProperties)i.style(r, e, t.orig[e]);
                        t.complete.call(r)
                    }
                    return !1
                }
                return t.duration == Infinity ? this.now = u : (f = u - this.startTime, this.state = f / t.duration, this.pos = i.easing[t.animatedProperties[this.prop]](this.state, f, 0, 1, t.duration), this.now = this.start + (this.end - this.start) * this.pos), this.update(), !0
            }
        };
        i.extend(i.fx, {
            tick: function () {
                for (var n = i.timers, t = 0; t < n.length; ++t)n[t]() || n.splice(t--, 1);
                n.length || i.fx.stop()
            }, interval: 13, stop: function () {
                clearInterval(it);
                it = null
            }, speeds: {slow: 600, fast: 200, _default: 400}, step: {
                opacity: function (n) {
                    i.style(n.elem, "opacity", n.now)
                }, _default: function (n) {
                    n.elem.style && n.elem.style[n.prop] != null ? n.elem.style[n.prop] = (n.prop === "width" || n.prop === "height" ? Math.max(0, n.now) : n.now) + n.unit : n.elem[n.prop] = n.now
                }
            }
        });
        i.expr && i.expr.filters && (i.expr.filters.animated = function (n) {
            return i.grep(i.timers, function (t) {
                return n === t.elem
            }).length
        });
        wr = /^t(?:able|d|h)$/i;
        bt = /^(?:body|html)$/i;
        i.fn.offset = "getBoundingClientRect"in r.documentElement ? function (n) {
            var t = this[0], r, f, u;
            if (n)return this.each(function (t) {
                i.offset.setOffset(this, n, t)
            });
            if (!t || !t.ownerDocument)return null;
            if (t === t.ownerDocument.body)return i.offset.bodyOffset(t);
            try {
                r = t.getBoundingClientRect()
            } catch (y) {
            }
            if (f = t.ownerDocument, u = f.documentElement, !r || !i.contains(u, t))return r ? {
                top: r.top,
                left: r.left
            } : {top: 0, left: 0};
            var e = f.body, o = kt(f), s = u.clientTop || e.clientTop || 0, h = u.clientLeft || e.clientLeft || 0, c = o.pageYOffset || i.support.boxModel && u.scrollTop || e.scrollTop, l = o.pageXOffset || i.support.boxModel && u.scrollLeft || e.scrollLeft, a = r.top + c - s, v = r.left + l - h;
            return {top: a, left: v}
        } : function (n) {
            var t = this[0];
            if (n)return this.each(function (t) {
                i.offset.setOffset(this, n, t)
            });
            if (!t || !t.ownerDocument)return null;
            if (t === t.ownerDocument.body)return i.offset.bodyOffset(t);
            i.offset.initialize();
            for (var r, h = t.offsetParent, a = t, c = t.ownerDocument, l = c.documentElement, e = c.body, s = c.defaultView, o = s ? s.getComputedStyle(t, null) : t.currentStyle, u = t.offsetTop, f = t.offsetLeft; (t = t.parentNode) && t !== e && t !== l;) {
                if (i.offset.supportsFixedPosition && o.position === "fixed")break;
                r = s ? s.getComputedStyle(t, null) : t.currentStyle;
                u -= t.scrollTop;
                f -= t.scrollLeft;
                t === h && (u += t.offsetTop, f += t.offsetLeft, !i.offset.doesNotAddBorder || i.offset.doesAddBorderForTableAndCells && wr.test(t.nodeName) || (u += parseFloat(r.borderTopWidth) || 0, f += parseFloat(r.borderLeftWidth) || 0), a = h, h = t.offsetParent);
                i.offset.subtractsBorderForOverflowNotVisible && r.overflow !== "visible" && (u += parseFloat(r.borderTopWidth) || 0, f += parseFloat(r.borderLeftWidth) || 0);
                o = r
            }
            return (o.position === "relative" || o.position === "static") && (u += e.offsetTop, f += e.offsetLeft), i.offset.supportsFixedPosition && o.position === "fixed" && (u += Math.max(l.scrollTop, e.scrollTop), f += Math.max(l.scrollLeft, e.scrollLeft)), {
                top: u,
                left: f
            }
        };
        i.offset = {
            initialize: function () {
                var t = r.body, u = r.createElement("div"), f, n, e, o = parseFloat(i.css(t, "marginTop")) || 0;
                i.extend(u.style, {
                    position: "absolute",
                    top: 0,
                    left: 0,
                    margin: 0,
                    border: 0,
                    width: "1px",
                    height: "1px",
                    visibility: "hidden"
                });
                u.innerHTML = "<div style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;'><div><\/div><\/div><table style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;' cellpadding='0' cellspacing='0'><tr><td><\/td><\/tr><\/table>";
                t.insertBefore(u, t.firstChild);
                f = u.firstChild;
                n = f.firstChild;
                e = f.nextSibling.firstChild.firstChild;
                this.doesNotAddBorder = n.offsetTop !== 5;
                this.doesAddBorderForTableAndCells = e.offsetTop === 5;
                n.style.position = "fixed";
                n.style.top = "20px";
                this.supportsFixedPosition = n.offsetTop === 20 || n.offsetTop === 15;
                n.style.position = n.style.top = "";
                f.style.overflow = "hidden";
                f.style.position = "relative";
                this.subtractsBorderForOverflowNotVisible = n.offsetTop === -5;
                this.doesNotIncludeMarginInBodyOffset = t.offsetTop !== o;
                t.removeChild(u);
                i.offset.initialize = i.noop
            }, bodyOffset: function (n) {
                var t = n.offsetTop, r = n.offsetLeft;
                return i.offset.initialize(), i.offset.doesNotIncludeMarginInBodyOffset && (t += parseFloat(i.css(n, "marginTop")) || 0, r += parseFloat(i.css(n, "marginLeft")) || 0), {
                    top: t,
                    left: r
                }
            }, setOffset: function (n, t, r) {
                var f = i.css(n, "position");
                f === "static" && (n.style.position = "relative");
                var e = i(n), o = e.offset(), l = i.css(n, "top"), a = i.css(n, "left"), v = (f === "absolute" || f === "fixed") && i.inArray("auto", [l, a]) > -1, u = {}, s = {}, h, c;
                v ? (s = e.position(), h = s.top, c = s.left) : (h = parseFloat(l) || 0, c = parseFloat(a) || 0);
                i.isFunction(t) && (t = t.call(n, r, o));
                t.top != null && (u.top = t.top - o.top + h);
                t.left != null && (u.left = t.left - o.left + c);
                "using"in t ? t.using.call(n, u) : e.css(u)
            }
        };
        i.fn.extend({
            position: function () {
                if (!this[0])return null;
                var u = this[0], n = this.offsetParent(), t = this.offset(), r = bt.test(n[0].nodeName) ? {
                    top: 0,
                    left: 0
                } : n.offset();
                return t.top -= parseFloat(i.css(u, "marginTop")) || 0, t.left -= parseFloat(i.css(u, "marginLeft")) || 0, r.top += parseFloat(i.css(n[0], "borderTopWidth")) || 0, r.left += parseFloat(i.css(n[0], "borderLeftWidth")) || 0, {
                    top: t.top - r.top,
                    left: t.left - r.left
                }
            }, offsetParent: function () {
                return this.map(function () {
                    for (var n = this.offsetParent || r.body; n && !bt.test(n.nodeName) && i.css(n, "position") === "static";)n = n.offsetParent;
                    return n
                })
            }
        });
        i.each(["Left", "Top"], function (n, r) {
            var u = "scroll" + r;
            i.fn[u] = function (r) {
                var e, f;
                return r === t ? (e = this[0], !e) ? null : (f = kt(e), f ? "pageXOffset"in f ? f[n ? "pageYOffset" : "pageXOffset"] : i.support.boxModel && f.document.documentElement[u] || f.document.body[u] : e[u]) : this.each(function () {
                    f = kt(this);
                    f ? f.scrollTo(n ? i(f).scrollLeft() : r, n ? r : i(f).scrollTop()) : this[u] = r
                })
            }
        });
        i.each(["Height", "Width"], function (n, r) {
            var u = r.toLowerCase();
            i.fn["inner" + r] = function () {
                var n = this[0];
                return n && n.style ? parseFloat(i.css(n, u, "padding")) : null
            };
            i.fn["outer" + r] = function (n) {
                var t = this[0];
                return t && t.style ? parseFloat(i.css(t, u, n ? "margin" : "border")) : null
            };
            i.fn[u] = function (n) {
                var f = this[0], e, o, s, h;
                return f ? i.isFunction(n) ? this.each(function (t) {
                    var r = i(this);
                    r[u](n.call(this, t, r[u]()))
                }) : i.isWindow(f) ? (e = f.document.documentElement["client" + r], o = f.document.body, f.document.compatMode === "CSS1Compat" && e || o && o["client" + r] || e) : f.nodeType === 9 ? Math.max(f.documentElement["client" + r], f.body["scroll" + r], f.documentElement["scroll" + r], f.body["offset" + r], f.documentElement["offset" + r]) : n === t ? (s = i.css(f, u), h = parseFloat(s), i.isNaN(h) ? s : h) : this.css(u, typeof n == "string" ? n : n + "px") : n == null ? null : this
            }
        });
        n.jQuery = n.$ = i
    }(window), !function (n, t) {
        "object" == typeof module && "object" == typeof module.exports ? module.exports = n.document ? t(n, !0) : function (n) {
            if (!n.document)throw new Error("jQuery requires a window with a document");
            return t(n)
        } : t(n)
    }("undefined" != typeof window ? window : this, function (n, t) {
        function ui(n) {
            var t = n.length, r = i.type(n);
            return "function" === r || i.isWindow(n) ? !1 : 1 === n.nodeType && t ? !0 : "array" === r || 0 === t || "number" == typeof t && t > 0 && t - 1 in n
        }

        function fi(n, t, r) {
            if (i.isFunction(t))return i.grep(n, function (n, i) {
                return !!t.call(n, i, n) !== r
            });
            if (t.nodeType)return i.grep(n, function (n) {
                return n === t !== r
            });
            if ("string" == typeof t) {
                if (ef.test(t))return i.filter(t, n, r);
                t = i.filter(t, n)
            }
            return i.grep(n, function (n) {
                return et.call(t, n) >= 0 !== r
            })
        }

        function rr(n, t) {
            while ((n = n[t]) && 1 !== n.nodeType);
            return n
        }

        function of(n) {
            var t = ei[n] = {};
            return i.each(n.match(c) || [], function (n, i) {
                t[i] = !0
            }), t
        }

        function ct() {
            u.removeEventListener("DOMContentLoaded", ct, !1);
            n.removeEventListener("load", ct, !1);
            i.ready()
        }

        function p() {
            Object.defineProperty(this.cache = {}, 0, {
                get: function () {
                    return {}
                }
            });
            this.expando = i.expando + Math.random()
        }

        function ur(n, t, r) {
            var u;
            if (void 0 === r && 1 === n.nodeType)if (u = "data-" + t.replace(hf, "-$1").toLowerCase(), r = n.getAttribute(u), "string" == typeof r) {
                try {
                    r = "true" === r ? !0 : "false" === r ? !1 : "null" === r ? null : +r + "" === r ? +r : sf.test(r) ? i.parseJSON(r) : r
                } catch (f) {
                }
                e.set(n, t, r)
            } else r = void 0;
            return r
        }

        function at() {
            return !0
        }

        function g() {
            return !1
        }

        function sr() {
            try {
                return u.activeElement
            } catch (n) {
            }
        }

        function ar(n, t) {
            return i.nodeName(n, "table") && i.nodeName(11 !== t.nodeType ? t : t.firstChild, "tr") ? n.getElementsByTagName("tbody")[0] || n.appendChild(n.ownerDocument.createElement("tbody")) : n
        }

        function bf(n) {
            return n.type = (null !== n.getAttribute("type")) + "/" + n.type, n
        }

        function kf(n) {
            var t = pf.exec(n.type);
            return t ? n.type = t[1] : n.removeAttribute("type"), n
        }

        function oi(n, t) {
            for (var i = 0, u = n.length; u > i; i++)r.set(n[i], "globalEval", !t || r.get(t[i], "globalEval"))
        }

        function vr(n, t) {
            var u, c, f, s, h, l, a, o;
            if (1 === t.nodeType) {
                if (r.hasData(n) && (s = r.access(n), h = r.set(t, s), o = s.events)) {
                    delete h.handle;
                    h.events = {};
                    for (f in o)for (u = 0, c = o[f].length; c > u; u++)i.event.add(t, f, o[f][u])
                }
                e.hasData(n) && (l = e.access(n), a = i.extend({}, l), e.set(t, a))
            }
        }

        function o(n, t) {
            var r = n.getElementsByTagName ? n.getElementsByTagName(t || "*") : n.querySelectorAll ? n.querySelectorAll(t || "*") : [];
            return void 0 === t || t && i.nodeName(n, t) ? i.merge([n], r) : r
        }

        function df(n, t) {
            var i = t.nodeName.toLowerCase();
            "input" === i && fr.test(n.type) ? t.checked = n.checked : ("input" === i || "textarea" === i) && (t.defaultValue = n.defaultValue)
        }

        function yr(t, r) {
            var u = i(r.createElement(t)).appendTo(r.body), f = n.getDefaultComputedStyle ? n.getDefaultComputedStyle(u[0]).display : i.css(u[0], "display");
            return u.detach(), f
        }

        function pr(n) {
            var r = u, t = si[n];
            return t || (t = yr(n, r), "none" !== t && t || (vt = (vt || i("<iframe frameborder='0' width='0' height='0'/>")).appendTo(r.documentElement), r = vt[0].contentDocument, r.write(), r.close(), t = yr(n, r), vt.detach()), si[n] = t), t
        }

        function rt(n, t, r) {
            var e, o, s, u, f = n.style;
            return r = r || yt(n), r && (u = r.getPropertyValue(t) || r[t]), r && ("" !== u || i.contains(n.ownerDocument, n) || (u = i.style(n, t)), hi.test(u) && wr.test(t) && (e = f.width, o = f.minWidth, s = f.maxWidth, f.minWidth = f.maxWidth = f.width = u, u = r.width, f.width = e, f.minWidth = o, f.maxWidth = s)), void 0 !== u ? u + "" : u
        }

        function br(n, t) {
            return {
                get: function () {
                    return n() ? void delete this.get : (this.get = t).apply(this, arguments)
                }
            }
        }

        function gr(n, t) {
            if (t in n)return t;
            for (var r = t[0].toUpperCase() + t.slice(1), u = t, i = dr.length; i--;)if (t = dr[i] + r, t in n)return t;
            return u
        }

        function nu(n, t, i) {
            var r = ne.exec(t);
            return r ? Math.max(0, r[1] - (i || 0)) + (r[2] || "px") : t
        }

        function tu(n, t, r, u, f) {
            for (var e = r === (u ? "border" : "content") ? 4 : "width" === t ? 1 : 0, o = 0; 4 > e; e += 2)"margin" === r && (o += i.css(n, r + w[e], !0, f)), u ? ("content" === r && (o -= i.css(n, "padding" + w[e], !0, f)), "margin" !== r && (o -= i.css(n, "border" + w[e] + "Width", !0, f))) : (o += i.css(n, "padding" + w[e], !0, f), "padding" !== r && (o += i.css(n, "border" + w[e] + "Width", !0, f)));
            return o
        }

        function iu(n, t, r) {
            var o = !0, u = "width" === t ? n.offsetWidth : n.offsetHeight, e = yt(n), s = "border-box" === i.css(n, "boxSizing", !1, e);
            if (0 >= u || null == u) {
                if (u = rt(n, t, e), (0 > u || null == u) && (u = n.style[t]), hi.test(u))return u;
                o = s && (f.boxSizingReliable() || u === n.style[t]);
                u = parseFloat(u) || 0
            }
            return u + tu(n, t, r || (s ? "border" : "content"), o, e) + "px"
        }

        function ru(n, t) {
            for (var e, u, s, o = [], f = 0, h = n.length; h > f; f++)u = n[f], u.style && (o[f] = r.get(u, "olddisplay"), e = u.style.display, t ? (o[f] || "none" !== e || (u.style.display = ""), "" === u.style.display && it(u) && (o[f] = r.access(u, "olddisplay", pr(u.nodeName)))) : o[f] || (s = it(u), (e && "none" !== e || !s) && r.set(u, "olddisplay", s ? e : i.css(u, "display"))));
            for (f = 0; h > f; f++)u = n[f], u.style && (t && "none" !== u.style.display && "" !== u.style.display || (u.style.display = t ? o[f] || "" : "none"));
            return n
        }

        function s(n, t, i, r, u) {
            return new s.prototype.init(n, t, i, r, u)
        }

        function fu() {
            return setTimeout(function () {
                nt = void 0
            }), nt = i.now()
        }

        function bt(n, t) {
            var r, u = 0, i = {height: n};
            for (t = t ? 1 : 0; 4 > u; u += 2 - t)r = w[u], i["margin" + r] = i["padding" + r] = n;
            return t && (i.opacity = i.width = n), i
        }

        function eu(n, t, i) {
            for (var u, f = (ut[t] || []).concat(ut["*"]), r = 0, e = f.length; e > r; r++)if (u = f[r].call(i, t, n))return u
        }

        function fe(n, t, u) {
            var f, l, p, a, o, w, y, c = this, v = {}, s = n.style, h = n.nodeType && it(n), e = r.get(n, "fxshow");
            u.queue || (o = i._queueHooks(n, "fx"), null == o.unqueued && (o.unqueued = 0, w = o.empty.fire, o.empty.fire = function () {
                o.unqueued || w()
            }), o.unqueued++, c.always(function () {
                c.always(function () {
                    o.unqueued--;
                    i.queue(n, "fx").length || o.empty.fire()
                })
            }));
            1 === n.nodeType && ("height"in t || "width"in t) && (u.overflow = [s.overflow, s.overflowX, s.overflowY], y = i.css(n, "display"), "none" === y && (y = pr(n.nodeName)), "inline" === y && "none" === i.css(n, "float") && (s.display = "inline-block"));
            u.overflow && (s.overflow = "hidden", c.always(function () {
                s.overflow = u.overflow[0];
                s.overflowX = u.overflow[1];
                s.overflowY = u.overflow[2]
            }));
            for (f in t)if (l = t[f], re.exec(l)) {
                if (delete t[f], p = p || "toggle" === l, l === (h ? "hide" : "show")) {
                    if ("show" !== l || !e || void 0 === e[f])continue;
                    h = !0
                }
                v[f] = e && e[f] || i.style(n, f)
            }
            if (!i.isEmptyObject(v)) {
                e ? "hidden"in e && (h = e.hidden) : e = r.access(n, "fxshow", {});
                p && (e.hidden = !h);
                h ? i(n).show() : c.done(function () {
                    i(n).hide()
                });
                c.done(function () {
                    var t;
                    r.remove(n, "fxshow");
                    for (t in v)i.style(n, t, v[t])
                });
                for (f in v)a = eu(h ? e[f] : 0, f, c), f in e || (e[f] = a.start, h && (a.end = a.start, a.start = "width" === f || "height" === f ? 1 : 0))
            }
        }

        function ee(n, t) {
            var r, f, e, u, o;
            for (r in n)if (f = i.camelCase(r), e = t[f], u = n[r], i.isArray(u) && (e = u[1], u = n[r] = u[0]), r !== f && (n[f] = u, delete n[r]), o = i.cssHooks[f], o && "expand"in o) {
                u = o.expand(u);
                delete n[f];
                for (r in u)r in n || (n[r] = u[r], t[r] = e)
            } else t[f] = e
        }

        function ou(n, t, r) {
            var h, e, o = 0, l = wt.length, f = i.Deferred().always(function () {
                delete c.elem
            }), c = function () {
                if (e)return !1;
                for (var s = nt || fu(), t = Math.max(0, u.startTime + u.duration - s), h = t / u.duration || 0, i = 1 - h, r = 0, o = u.tweens.length; o > r; r++)u.tweens[r].run(i);
                return f.notifyWith(n, [u, i, t]), 1 > i && o ? t : (f.resolveWith(n, [u]), !1)
            }, u = f.promise({
                elem: n,
                props: i.extend({}, t),
                opts: i.extend(!0, {specialEasing: {}}, r),
                originalProperties: t,
                originalOptions: r,
                startTime: nt || fu(),
                duration: r.duration,
                tweens: [],
                createTween: function (t, r) {
                    var f = i.Tween(n, u.opts, t, r, u.opts.specialEasing[t] || u.opts.easing);
                    return u.tweens.push(f), f
                },
                stop: function (t) {
                    var i = 0, r = t ? u.tweens.length : 0;
                    if (e)return this;
                    for (e = !0; r > i; i++)u.tweens[i].run(1);
                    return t ? f.resolveWith(n, [u, t]) : f.rejectWith(n, [u, t]), this
                }
            }), s = u.props;
            for (ee(s, u.opts.specialEasing); l > o; o++)if (h = wt[o].call(u, n, s, u.opts))return h;
            return i.map(s, eu, u), i.isFunction(u.opts.start) && u.opts.start.call(n, u), i.fx.timer(i.extend(c, {
                elem: n,
                anim: u,
                queue: u.opts.queue
            })), u.progress(u.opts.progress).done(u.opts.done, u.opts.complete).fail(u.opts.fail).always(u.opts.always)
        }

        function pu(n) {
            return function (t, r) {
                "string" != typeof t && (r = t, t = "*");
                var u, f = 0, e = t.toLowerCase().match(c) || [];
                if (i.isFunction(r))while (u = e[f++])"+" === u[0] ? (u = u.slice(1) || "*", (n[u] = n[u] || []).unshift(r)) : (n[u] = n[u] || []).push(r)
            }
        }

        function wu(n, t, r, u) {
            function e(s) {
                var h;
                return f[s] = !0, i.each(n[s] || [], function (n, i) {
                    var s = i(t, r, u);
                    return "string" != typeof s || o || f[s] ? o ? !(h = s) : void 0 : (t.dataTypes.unshift(s), e(s), !1)
                }), h
            }

            var f = {}, o = n === ci;
            return e(t.dataTypes[0]) || !f["*"] && e("*")
        }

        function li(n, t) {
            var r, u, f = i.ajaxSettings.flatOptions || {};
            for (r in t)void 0 !== t[r] && ((f[r] ? n : u || (u = {}))[r] = t[r]);
            return u && i.extend(!0, n, u), n
        }

        function ae(n, t, i) {
            for (var e, u, f, o, s = n.contents, r = n.dataTypes; "*" === r[0];)r.shift(), void 0 === e && (e = n.mimeType || t.getResponseHeader("Content-Type"));
            if (e)for (u in s)if (s[u] && s[u].test(e)) {
                r.unshift(u);
                break
            }
            if (r[0]in i)f = r[0]; else {
                for (u in i) {
                    if (!r[0] || n.converters[u + " " + r[0]]) {
                        f = u;
                        break
                    }
                    o || (o = u)
                }
                f = f || o
            }
            if (f)return (f !== r[0] && r.unshift(f), i[f])
        }

        function ve(n, t, i, r) {
            var h, u, f, s, e, o = {}, c = n.dataTypes.slice();
            if (c[1])for (f in n.converters)o[f.toLowerCase()] = n.converters[f];
            for (u = c.shift(); u;)if (n.responseFields[u] && (i[n.responseFields[u]] = t), !e && r && n.dataFilter && (t = n.dataFilter(t, n.dataType)), e = u, u = c.shift())if ("*" === u)u = e; else if ("*" !== e && e !== u) {
                if (f = o[e + " " + u] || o["* " + u], !f)for (h in o)if (s = h.split(" "), s[1] === u && (f = o[e + " " + s[0]] || o["* " + s[0]])) {
                    f === !0 ? f = o[h] : o[h] !== !0 && (u = s[0], c.unshift(s[1]));
                    break
                }
                if (f !== !0)if (f && n.throws)t = f(t); else try {
                    t = f(t)
                } catch (l) {
                    return {state: "parsererror", error: f ? l : "No conversion from " + e + " to " + u}
                }
            }
            return {state: "success", data: t}
        }

        function ai(n, t, r, u) {
            var f;
            if (i.isArray(t))i.each(t, function (t, i) {
                r || pe.test(n) ? u(n, i) : ai(n + "[" + ("object" == typeof i ? t : "") + "]", i, r, u)
            }); else if (r || "object" !== i.type(t))u(n, t); else for (f in t)ai(n + "[" + f + "]", t[f], r, u)
        }

        function ku(n) {
            return i.isWindow(n) ? n : 9 === n.nodeType && n.defaultView
        }

        var k = [], a = k.slice, wi = k.concat, ii = k.push, et = k.indexOf, ot = {}, nf = ot.toString, ri = ot.hasOwnProperty, tf = "".trim, f = {}, u = n.document, bi = "2.1.0", i = function (n, t) {
            return new i.fn.init(n, t)
        }, rf = /^-ms-/, uf = /-([\da-z])/gi, ff = function (n, t) {
            return t.toUpperCase()
        }, y, st, gi, nr, tr, ir, c, ei, ht, l, d, vt, si, oe, su, tt, hu, kt, cu, dt, gt, vi, ti, yi, pi, du, gu;
        i.fn = i.prototype = {
            jquery: bi, constructor: i, selector: "", length: 0, toArray: function () {
                return a.call(this)
            }, get: function (n) {
                return null != n ? 0 > n ? this[n + this.length] : this[n] : a.call(this)
            }, pushStack: function (n) {
                var t = i.merge(this.constructor(), n);
                return t.prevObject = this, t.context = this.context, t
            }, each: function (n, t) {
                return i.each(this, n, t)
            }, map: function (n) {
                return this.pushStack(i.map(this, function (t, i) {
                    return n.call(t, i, t)
                }))
            }, slice: function () {
                return this.pushStack(a.apply(this, arguments))
            }, first: function () {
                return this.eq(0)
            }, last: function () {
                return this.eq(-1)
            }, eq: function (n) {
                var i = this.length, t = +n + (0 > n ? i : 0);
                return this.pushStack(t >= 0 && i > t ? [this[t]] : [])
            }, end: function () {
                return this.prevObject || this.constructor(null)
            }, push: ii, sort: k.sort, splice: k.splice
        };
        i.extend = i.fn.extend = function () {
            var e, f, r, t, o, s, n = arguments[0] || {}, u = 1, c = arguments.length, h = !1;
            for ("boolean" == typeof n && (h = n, n = arguments[u] || {}, u++), "object" == typeof n || i.isFunction(n) || (n = {}), u === c && (n = this, u--); c > u; u++)if (null != (e = arguments[u]))for (f in e)r = n[f], t = e[f], n !== t && (h && t && (i.isPlainObject(t) || (o = i.isArray(t))) ? (o ? (o = !1, s = r && i.isArray(r) ? r : []) : s = r && i.isPlainObject(r) ? r : {}, n[f] = i.extend(h, s, t)) : void 0 !== t && (n[f] = t));
            return n
        };
        i.extend({
            expando: "jQuery" + (bi + Math.random()).replace(/\D/g, ""), isReady: !0, error: function (n) {
                throw new Error(n);
            }, noop: function () {
            }, isFunction: function (n) {
                return "function" === i.type(n)
            }, isArray: Array.isArray, isWindow: function (n) {
                return null != n && n === n.window
            }, isNumeric: function (n) {
                return n - parseFloat(n) >= 0
            }, isPlainObject: function (n) {
                if ("object" !== i.type(n) || n.nodeType || i.isWindow(n))return !1;
                try {
                    if (n.constructor && !ri.call(n.constructor.prototype, "isPrototypeOf"))return !1
                } catch (t) {
                    return !1
                }
                return !0
            }, isEmptyObject: function (n) {
                var t;
                for (t in n)return !1;
                return !0
            }, type: function (n) {
                return null == n ? n + "" : "object" == typeof n || "function" == typeof n ? ot[nf.call(n)] || "object" : typeof n
            }, globalEval: function (n) {
                var t, r = eval;
                n = i.trim(n);
                n && (1 === n.indexOf("use strict") ? (t = u.createElement("script"), t.text = n, u.head.appendChild(t).parentNode.removeChild(t)) : r(n))
            }, camelCase: function (n) {
                return n.replace(rf, "ms-").replace(uf, ff)
            }, nodeName: function (n, t) {
                return n.nodeName && n.nodeName.toLowerCase() === t.toLowerCase()
            }, each: function (n, t, i) {
                var u, r = 0, f = n.length, e = ui(n);
                if (i) {
                    if (e) {
                        for (; f > r; r++)if (u = t.apply(n[r], i), u === !1)break
                    } else for (r in n)if (u = t.apply(n[r], i), u === !1)break
                } else if (e) {
                    for (; f > r; r++)if (u = t.call(n[r], r, n[r]), u === !1)break
                } else for (r in n)if (u = t.call(n[r], r, n[r]), u === !1)break;
                return n
            }, trim: function (n) {
                return null == n ? "" : tf.call(n)
            }, makeArray: function (n, t) {
                var r = t || [];
                return null != n && (ui(Object(n)) ? i.merge(r, "string" == typeof n ? [n] : n) : ii.call(r, n)), r
            }, inArray: function (n, t, i) {
                return null == t ? -1 : et.call(t, n, i)
            }, merge: function (n, t) {
                for (var u = +t.length, i = 0, r = n.length; u > i; i++)n[r++] = t[i];
                return n.length = r, n
            }, grep: function (n, t, i) {
                for (var u, f = [], r = 0, e = n.length, o = !i; e > r; r++)u = !t(n[r], r), u !== o && f.push(n[r]);
                return f
            }, map: function (n, t, i) {
                var u, r = 0, e = n.length, o = ui(n), f = [];
                if (o)for (; e > r; r++)u = t(n[r], r, i), null != u && f.push(u); else for (r in n)u = t(n[r], r, i), null != u && f.push(u);
                return wi.apply([], f)
            }, guid: 1, proxy: function (n, t) {
                var u, f, r;
                return "string" == typeof t && (u = n[t], t = n, n = u), i.isFunction(n) ? (f = a.call(arguments, 2), r = function () {
                    return n.apply(t || this, f.concat(a.call(arguments)))
                }, r.guid = n.guid = n.guid || i.guid++, r) : void 0
            }, now: Date.now, support: f
        });
        i.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function (n, t) {
            ot["[object " + t + "]"] = t.toLowerCase()
        });
        y = function (n) {
            function u(n, t, i, u) {
                var w, h, c, v, k, y, d, a, nt, g;
                if ((t ? t.ownerDocument || t : s) !== e && p(t), t = t || e, i = i || [], !n || "string" != typeof n)return i;
                if (1 !== (v = t.nodeType) && 9 !== v)return [];
                if (l && !u) {
                    if (w = or.exec(n))if (c = w[1]) {
                        if (9 === v) {
                            if (h = t.getElementById(c), !h || !h.parentNode)return i;
                            if (h.id === c)return i.push(h), i
                        } else if (t.ownerDocument && (h = t.ownerDocument.getElementById(c)) && et(t, h) && h.id === c)return i.push(h), i
                    } else {
                        if (w[2])return b.apply(i, t.getElementsByTagName(n)), i;
                        if ((c = w[3]) && r.getElementsByClassName && t.getElementsByClassName)return b.apply(i, t.getElementsByClassName(c)), i
                    }
                    if (r.qsa && (!o || !o.test(n))) {
                        if (a = d = f, nt = t, g = 9 === v && n, 1 === v && "object" !== t.nodeName.toLowerCase()) {
                            for (y = vt(n), (d = t.getAttribute("id")) ? a = d.replace(sr, "\\$&") : t.setAttribute("id", a), a = "[id='" + a + "'] ", k = y.length; k--;)y[k] = a + yt(y[k]);
                            nt = gt.test(n) && ii(t.parentNode) || t;
                            g = y.join(",")
                        }
                        if (g)try {
                            return b.apply(i, nt.querySelectorAll(g)), i
                        } catch (tt) {
                        } finally {
                            d || t.removeAttribute("id")
                        }
                    }
                }
                return vr(n.replace(lt, "$1"), t, i, u)
            }

            function ni() {
                function n(r, u) {
                    return i.push(r + " ") > t.cacheLength && delete n[i.shift()], n[r + " "] = u
                }

                var i = [];
                return n
            }

            function h(n) {
                return n[f] = !0, n
            }

            function c(n) {
                var t = e.createElement("div");
                try {
                    return !!n(t)
                } catch (i) {
                    return !1
                } finally {
                    t.parentNode && t.parentNode.removeChild(t);
                    t = null
                }
            }

            function ti(n, i) {
                for (var u = n.split("|"), r = n.length; r--;)t.attrHandle[u[r]] = i
            }

            function pi(n, t) {
                var i = t && n, r = i && 1 === n.nodeType && 1 === t.nodeType && (~t.sourceIndex || li) - (~n.sourceIndex || li);
                if (r)return r;
                if (i)while (i = i.nextSibling)if (i === t)return -1;
                return n ? 1 : -1
            }

            function hr(n) {
                return function (t) {
                    var i = t.nodeName.toLowerCase();
                    return "input" === i && t.type === n
                }
            }

            function cr(n) {
                return function (t) {
                    var i = t.nodeName.toLowerCase();
                    return ("input" === i || "button" === i) && t.type === n
                }
            }

            function tt(n) {
                return h(function (t) {
                    return t = +t, h(function (i, r) {
                        for (var u, f = n([], i.length, t), e = f.length; e--;)i[u = f[e]] && (i[u] = !(r[u] = i[u]))
                    })
                })
            }

            function ii(n) {
                return n && typeof n.getElementsByTagName !== ut && n
            }

            function wi() {
            }

            function vt(n, i) {
                var e, f, s, o, r, h, c, l = hi[n + " "];
                if (l)return i ? 0 : l.slice(0);
                for (r = n, h = [], c = t.preFilter; r;) {
                    (!e || (f = nr.exec(r))) && (f && (r = r.slice(f[0].length) || r), h.push(s = []));
                    e = !1;
                    (f = tr.exec(r)) && (e = f.shift(), s.push({
                        value: e,
                        type: f[0].replace(lt, " ")
                    }), r = r.slice(e.length));
                    for (o in t.filter)(f = at[o].exec(r)) && (!c[o] || (f = c[o](f))) && (e = f.shift(), s.push({
                        value: e,
                        type: o,
                        matches: f
                    }), r = r.slice(e.length));
                    if (!e)break
                }
                return i ? r.length : r ? u.error(n) : hi(n, h).slice(0)
            }

            function yt(n) {
                for (var t = 0, r = n.length, i = ""; r > t; t++)i += n[t].value;
                return i
            }

            function ri(n, t, i) {
                var r = t.dir, u = i && "parentNode" === r, e = bi++;
                return t.first ? function (t, i, f) {
                    while (t = t[r])if (1 === t.nodeType || u)return n(t, i, f)
                } : function (t, i, o) {
                    var s, h, c = [a, e];
                    if (o) {
                        while (t = t[r])if ((1 === t.nodeType || u) && n(t, i, o))return !0
                    } else while (t = t[r])if (1 === t.nodeType || u) {
                        if (h = t[f] || (t[f] = {}), (s = h[r]) && s[0] === a && s[1] === e)return c[2] = s[2];
                        if (h[r] = c, c[2] = n(t, i, o))return !0
                    }
                }
            }

            function ui(n) {
                return n.length > 1 ? function (t, i, r) {
                    for (var u = n.length; u--;)if (!n[u](t, i, r))return !1;
                    return !0
                } : n[0]
            }

            function pt(n, t, i, r, u) {
                for (var e, o = [], f = 0, s = n.length, h = null != t; s > f; f++)(e = n[f]) && (!i || i(e, r, u)) && (o.push(e), h && t.push(f));
                return o
            }

            function fi(n, t, i, r, u, e) {
                return r && !r[f] && (r = fi(r)), u && !u[f] && (u = fi(u, e)), h(function (f, e, o, s) {
                    var l, c, a, p = [], y = [], w = e.length, k = f || ar(t || "*", o.nodeType ? [o] : o, []), v = !n || !f && t ? k : pt(k, p, n, o, s), h = i ? u || (f ? n : w || r) ? [] : e : v;
                    if (i && i(v, h, o, s), r)for (l = pt(h, y), r(l, [], o, s), c = l.length; c--;)(a = l[c]) && (h[y[c]] = !(v[y[c]] = a));
                    if (f) {
                        if (u || n) {
                            if (u) {
                                for (l = [], c = h.length; c--;)(a = h[c]) && l.push(v[c] = a);
                                u(null, h = [], l, s)
                            }
                            for (c = h.length; c--;)(a = h[c]) && (l = u ? nt.call(f, a) : p[c]) > -1 && (f[l] = !(e[l] = a))
                        }
                    } else h = pt(h === e ? h.splice(w, h.length) : h), u ? u(null, e, h, s) : b.apply(e, h)
                })
            }

            function ei(n) {
                for (var s, u, r, o = n.length, h = t.relative[n[0].type], c = h || t.relative[" "], i = h ? 1 : 0, l = ri(function (n) {
                    return n === s
                }, c, !0), a = ri(function (n) {
                    return nt.call(s, n) > -1
                }, c, !0), e = [function (n, t, i) {
                    return !h && (i || t !== ht) || ((s = t).nodeType ? l(n, t, i) : a(n, t, i))
                }]; o > i; i++)if (u = t.relative[n[i].type])e = [ri(ui(e), u)]; else {
                    if (u = t.filter[n[i].type].apply(null, n[i].matches), u[f]) {
                        for (r = ++i; o > r; r++)if (t.relative[n[r].type])break;
                        return fi(i > 1 && ui(e), i > 1 && yt(n.slice(0, i - 1).concat({value: " " === n[i - 2].type ? "*" : ""})).replace(lt, "$1"), u, r > i && ei(n.slice(i, r)), o > r && ei(n = n.slice(r)), o > r && yt(n))
                    }
                    e.push(u)
                }
                return ui(e)
            }

            function lr(n, i) {
                var r = i.length > 0, f = n.length > 0, o = function (o, s, h, c, l) {
                    var y, d, w, k = 0, v = "0", g = o && [], p = [], nt = ht, tt = o || f && t.find.TAG("*", l), it = a += null == nt ? 1 : Math.random() || .1, rt = tt.length;
                    for (l && (ht = s !== e && s); v !== rt && null != (y = tt[v]); v++) {
                        if (f && y) {
                            for (d = 0; w = n[d++];)if (w(y, s, h)) {
                                c.push(y);
                                break
                            }
                            l && (a = it)
                        }
                        r && ((y = !w && y) && k--, o && g.push(y))
                    }
                    if (k += v, r && v !== k) {
                        for (d = 0; w = i[d++];)w(g, p, s, h);
                        if (o) {
                            if (k > 0)while (v--)g[v] || p[v] || (p[v] = di.call(c));
                            p = pt(p)
                        }
                        b.apply(c, p);
                        l && !o && p.length > 0 && k + i.length > 1 && u.uniqueSort(c)
                    }
                    return l && (a = it, ht = nt), g
                };
                return r ? h(o) : o
            }

            function ar(n, t, i) {
                for (var r = 0, f = t.length; f > r; r++)u(n, t[r], i);
                return i
            }

            function vr(n, i, u, f) {
                var s, e, o, c, a, h = vt(n);
                if (!f && 1 === h.length) {
                    if (e = h[0] = h[0].slice(0), e.length > 2 && "ID" === (o = e[0]).type && r.getById && 9 === i.nodeType && l && t.relative[e[1].type]) {
                        if (i = (t.find.ID(o.matches[0].replace(k, d), i) || [])[0], !i)return u;
                        n = n.slice(e.shift().value.length)
                    }
                    for (s = at.needsContext.test(n) ? 0 : e.length; s--;) {
                        if (o = e[s], t.relative[c = o.type])break;
                        if ((a = t.find[c]) && (f = a(o.matches[0].replace(k, d), gt.test(e[0].type) && ii(i.parentNode) || i))) {
                            if (e.splice(s, 1), n = f.length && yt(e), !n)return b.apply(u, f), u;
                            break
                        }
                    }
                }
                return wt(n, h)(f, i, !l, u, gt.test(n) && ii(i.parentNode) || i), u
            }

            var it, r, t, st, oi, wt, ht, y, rt, p, e, v, l, o, g, ct, et, f = "sizzle" + -new Date, s = n.document, a = 0, bi = 0, si = ni(), hi = ni(), ci = ni(), bt = function (n, t) {
                return n === t && (rt = !0), 0
            }, ut = "undefined", li = -2147483648, ki = {}.hasOwnProperty, w = [], di = w.pop, gi = w.push, b = w.push, ai = w.slice, nt = w.indexOf || function (n) {
                    for (var t = 0, i = this.length; i > t; t++)if (this[t] === n)return t;
                    return -1
                }, kt = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped", i = "[\\x20\\t\\r\\n\\f]", ft = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+", vi = ft.replace("w", "w#"), yi = "\\[" + i + "*(" + ft + ")" + i + "*(?:([*^$|!~]?=)" + i + "*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + vi + ")|)|)" + i + "*\\]", dt = ":(" + ft + ")(?:\\(((['\"])((?:\\\\.|[^\\\\])*?)\\3|((?:\\\\.|[^\\\\()[\\]]|" + yi.replace(3, 8) + ")*)|.*)\\)|)", lt = new RegExp("^" + i + "+|((?:^|[^\\\\])(?:\\\\.)*)" + i + "+$", "g"), nr = new RegExp("^" + i + "*," + i + "*"), tr = new RegExp("^" + i + "*([>+~]|" + i + ")" + i + "*"), ir = new RegExp("=" + i + "*([^\\]'\"]*?)" + i + "*\\]", "g"), rr = new RegExp(dt), ur = new RegExp("^" + vi + "$"), at = {
                ID: new RegExp("^#(" + ft + ")"),
                CLASS: new RegExp("^\\.(" + ft + ")"),
                TAG: new RegExp("^(" + ft.replace("w", "w*") + ")"),
                ATTR: new RegExp("^" + yi),
                PSEUDO: new RegExp("^" + dt),
                CHILD: new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + i + "*(even|odd|(([+-]|)(\\d*)n|)" + i + "*(?:([+-]|)" + i + "*(\\d+)|))" + i + "*\\)|)", "i"),
                bool: new RegExp("^(?:" + kt + ")$", "i"),
                needsContext: new RegExp("^" + i + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + i + "*((?:-\\d)?\\d*)" + i + "*\\)|)(?=[^-]|$)", "i")
            }, fr = /^(?:input|select|textarea|button)$/i, er = /^h\d$/i, ot = /^[^{]+\{\s*\[native \w/, or = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/, gt = /[+~]/, sr = /'|\\/g, k = new RegExp("\\\\([\\da-f]{1,6}" + i + "?|(" + i + ")|.)", "ig"), d = function (n, t, i) {
                var r = "0x" + t - 65536;
                return r !== r || i ? t : 0 > r ? String.fromCharCode(r + 65536) : String.fromCharCode(r >> 10 | 55296, 1023 & r | 56320)
            };
            try {
                b.apply(w = ai.call(s.childNodes), s.childNodes);
                w[s.childNodes.length].nodeType
            } catch (yr) {
                b = {
                    apply: w.length ? function (n, t) {
                        gi.apply(n, ai.call(t))
                    } : function (n, t) {
                        for (var i = n.length, r = 0; n[i++] = t[r++];);
                        n.length = i - 1
                    }
                }
            }
            r = u.support = {};
            oi = u.isXML = function (n) {
                var t = n && (n.ownerDocument || n).documentElement;
                return t ? "HTML" !== t.nodeName : !1
            };
            p = u.setDocument = function (n) {
                var a, u = n ? n.ownerDocument || n : s, h = u.defaultView;
                return u !== e && 9 === u.nodeType && u.documentElement ? (e = u, v = u.documentElement, l = !oi(u), h && h !== h.top && (h.addEventListener ? h.addEventListener("unload", function () {
                    p()
                }, !1) : h.attachEvent && h.attachEvent("onunload", function () {
                    p()
                })), r.attributes = c(function (n) {
                    return n.className = "i", !n.getAttribute("className")
                }), r.getElementsByTagName = c(function (n) {
                    return n.appendChild(u.createComment("")), !n.getElementsByTagName("*").length
                }), r.getElementsByClassName = ot.test(u.getElementsByClassName) && c(function (n) {
                        return n.innerHTML = "<div class='a'><\/div><div class='a i'><\/div>", n.firstChild.className = "i", 2 === n.getElementsByClassName("i").length
                    }), r.getById = c(function (n) {
                    return v.appendChild(n).id = f, !u.getElementsByName || !u.getElementsByName(f).length
                }), r.getById ? (t.find.ID = function (n, t) {
                    if (typeof t.getElementById !== ut && l) {
                        var i = t.getElementById(n);
                        return i && i.parentNode ? [i] : []
                    }
                }, t.filter.ID = function (n) {
                    var t = n.replace(k, d);
                    return function (n) {
                        return n.getAttribute("id") === t
                    }
                }) : (delete t.find.ID, t.filter.ID = function (n) {
                    var t = n.replace(k, d);
                    return function (n) {
                        var i = typeof n.getAttributeNode !== ut && n.getAttributeNode("id");
                        return i && i.value === t
                    }
                }), t.find.TAG = r.getElementsByTagName ? function (n, t) {
                    if (typeof t.getElementsByTagName !== ut)return t.getElementsByTagName(n)
                } : function (n, t) {
                    var i, r = [], f = 0, u = t.getElementsByTagName(n);
                    if ("*" === n) {
                        while (i = u[f++])1 === i.nodeType && r.push(i);
                        return r
                    }
                    return u
                }, t.find.CLASS = r.getElementsByClassName && function (n, t) {
                        if (typeof t.getElementsByClassName !== ut && l)return t.getElementsByClassName(n)
                    }, g = [], o = [], (r.qsa = ot.test(u.querySelectorAll)) && (c(function (n) {
                    n.innerHTML = "<select t=''><option selected=''><\/option><\/select>";
                    n.querySelectorAll("[t^='']").length && o.push("[*^$]=" + i + "*(?:''|\"\")");
                    n.querySelectorAll("[selected]").length || o.push("\\[" + i + "*(?:value|" + kt + ")");
                    n.querySelectorAll(":checked").length || o.push(":checked")
                }), c(function (n) {
                    var t = u.createElement("input");
                    t.setAttribute("type", "hidden");
                    n.appendChild(t).setAttribute("name", "D");
                    n.querySelectorAll("[name=d]").length && o.push("name" + i + "*[*^$|!~]?=");
                    n.querySelectorAll(":enabled").length || o.push(":enabled", ":disabled");
                    n.querySelectorAll("*,:x");
                    o.push(",.*:")
                })), (r.matchesSelector = ot.test(ct = v.webkitMatchesSelector || v.mozMatchesSelector || v.oMatchesSelector || v.msMatchesSelector)) && c(function (n) {
                    r.disconnectedMatch = ct.call(n, "div");
                    ct.call(n, "[s!='']:x");
                    g.push("!=", dt)
                }), o = o.length && new RegExp(o.join("|")), g = g.length && new RegExp(g.join("|")), a = ot.test(v.compareDocumentPosition), et = a || ot.test(v.contains) ? function (n, t) {
                    var r = 9 === n.nodeType ? n.documentElement : n, i = t && t.parentNode;
                    return n === i || !(!i || 1 !== i.nodeType || !(r.contains ? r.contains(i) : n.compareDocumentPosition && 16 & n.compareDocumentPosition(i)))
                } : function (n, t) {
                    if (t)while (t = t.parentNode)if (t === n)return !0;
                    return !1
                }, bt = a ? function (n, t) {
                    if (n === t)return rt = !0, 0;
                    var i = !n.compareDocumentPosition - !t.compareDocumentPosition;
                    return i ? i : (i = (n.ownerDocument || n) === (t.ownerDocument || t) ? n.compareDocumentPosition(t) : 1, 1 & i || !r.sortDetached && t.compareDocumentPosition(n) === i ? n === u || n.ownerDocument === s && et(s, n) ? -1 : t === u || t.ownerDocument === s && et(s, t) ? 1 : y ? nt.call(y, n) - nt.call(y, t) : 0 : 4 & i ? -1 : 1)
                } : function (n, t) {
                    if (n === t)return rt = !0, 0;
                    var i, r = 0, o = n.parentNode, h = t.parentNode, f = [n], e = [t];
                    if (!o || !h)return n === u ? -1 : t === u ? 1 : o ? -1 : h ? 1 : y ? nt.call(y, n) - nt.call(y, t) : 0;
                    if (o === h)return pi(n, t);
                    for (i = n; i = i.parentNode;)f.unshift(i);
                    for (i = t; i = i.parentNode;)e.unshift(i);
                    while (f[r] === e[r])r++;
                    return r ? pi(f[r], e[r]) : f[r] === s ? -1 : e[r] === s ? 1 : 0
                }, u) : e
            };
            u.matches = function (n, t) {
                return u(n, null, null, t)
            };
            u.matchesSelector = function (n, t) {
                if ((n.ownerDocument || n) !== e && p(n), t = t.replace(ir, "='$1']"), !(!r.matchesSelector || !l || g && g.test(t) || o && o.test(t)))try {
                    var i = ct.call(n, t);
                    if (i || r.disconnectedMatch || n.document && 11 !== n.document.nodeType)return i
                } catch (f) {
                }
                return u(t, e, null, [n]).length > 0
            };
            u.contains = function (n, t) {
                return (n.ownerDocument || n) !== e && p(n), et(n, t)
            };
            u.attr = function (n, i) {
                (n.ownerDocument || n) !== e && p(n);
                var f = t.attrHandle[i.toLowerCase()], u = f && ki.call(t.attrHandle, i.toLowerCase()) ? f(n, i, !l) : void 0;
                return void 0 !== u ? u : r.attributes || !l ? n.getAttribute(i) : (u = n.getAttributeNode(i)) && u.specified ? u.value : null
            };
            u.error = function (n) {
                throw new Error("Syntax error, unrecognized expression: " + n);
            };
            u.uniqueSort = function (n) {
                var u, f = [], t = 0, i = 0;
                if (rt = !r.detectDuplicates, y = !r.sortStable && n.slice(0), n.sort(bt), rt) {
                    while (u = n[i++])u === n[i] && (t = f.push(i));
                    while (t--)n.splice(f[t], 1)
                }
                return y = null, n
            };
            st = u.getText = function (n) {
                var r, i = "", u = 0, t = n.nodeType;
                if (t) {
                    if (1 === t || 9 === t || 11 === t) {
                        if ("string" == typeof n.textContent)return n.textContent;
                        for (n = n.firstChild; n; n = n.nextSibling)i += st(n)
                    } else if (3 === t || 4 === t)return n.nodeValue
                } else while (r = n[u++])i += st(r);
                return i
            };
            t = u.selectors = {
                cacheLength: 50,
                createPseudo: h,
                match: at,
                attrHandle: {},
                find: {},
                relative: {
                    ">": {dir: "parentNode", first: !0},
                    " ": {dir: "parentNode"},
                    "+": {dir: "previousSibling", first: !0},
                    "~": {dir: "previousSibling"}
                },
                preFilter: {
                    ATTR: function (n) {
                        return n[1] = n[1].replace(k, d), n[3] = (n[4] || n[5] || "").replace(k, d), "~=" === n[2] && (n[3] = " " + n[3] + " "), n.slice(0, 4)
                    }, CHILD: function (n) {
                        return n[1] = n[1].toLowerCase(), "nth" === n[1].slice(0, 3) ? (n[3] || u.error(n[0]), n[4] = +(n[4] ? n[5] + (n[6] || 1) : 2 * ("even" === n[3] || "odd" === n[3])), n[5] = +(n[7] + n[8] || "odd" === n[3])) : n[3] && u.error(n[0]), n
                    }, PSEUDO: function (n) {
                        var i, t = !n[5] && n[2];
                        return at.CHILD.test(n[0]) ? null : (n[3] && void 0 !== n[4] ? n[2] = n[4] : t && rr.test(t) && (i = vt(t, !0)) && (i = t.indexOf(")", t.length - i) - t.length) && (n[0] = n[0].slice(0, i), n[2] = t.slice(0, i)), n.slice(0, 3))
                    }
                },
                filter: {
                    TAG: function (n) {
                        var t = n.replace(k, d).toLowerCase();
                        return "*" === n ? function () {
                            return !0
                        } : function (n) {
                            return n.nodeName && n.nodeName.toLowerCase() === t
                        }
                    }, CLASS: function (n) {
                        var t = si[n + " "];
                        return t || (t = new RegExp("(^|" + i + ")" + n + "(" + i + "|$)")) && si(n, function (n) {
                                return t.test("string" == typeof n.className && n.className || typeof n.getAttribute !== ut && n.getAttribute("class") || "")
                            })
                    }, ATTR: function (n, t, i) {
                        return function (r) {
                            var f = u.attr(r, n);
                            return null == f ? "!=" === t : t ? (f += "", "=" === t ? f === i : "!=" === t ? f !== i : "^=" === t ? i && 0 === f.indexOf(i) : "*=" === t ? i && f.indexOf(i) > -1 : "$=" === t ? i && f.slice(-i.length) === i : "~=" === t ? (" " + f + " ").indexOf(i) > -1 : "|=" === t ? f === i || f.slice(0, i.length + 1) === i + "-" : !1) : !0
                        }
                    }, CHILD: function (n, t, i, r, u) {
                        var s = "nth" !== n.slice(0, 3), o = "last" !== n.slice(-4), e = "of-type" === t;
                        return 1 === r && 0 === u ? function (n) {
                            return !!n.parentNode
                        } : function (t, i, h) {
                            var v, k, c, l, y, w, b = s !== o ? "nextSibling" : "previousSibling", p = t.parentNode, g = e && t.nodeName.toLowerCase(), d = !h && !e;
                            if (p) {
                                if (s) {
                                    while (b) {
                                        for (c = t; c = c[b];)if (e ? c.nodeName.toLowerCase() === g : 1 === c.nodeType)return !1;
                                        w = b = "only" === n && !w && "nextSibling"
                                    }
                                    return !0
                                }
                                if (w = [o ? p.firstChild : p.lastChild], o && d) {
                                    for (k = p[f] || (p[f] = {}), v = k[n] || [], y = v[0] === a && v[1], l = v[0] === a && v[2], c = y && p.childNodes[y]; c = ++y && c && c[b] || (l = y = 0) || w.pop();)if (1 === c.nodeType && ++l && c === t) {
                                        k[n] = [a, y, l];
                                        break
                                    }
                                } else if (d && (v = (t[f] || (t[f] = {}))[n]) && v[0] === a)l = v[1]; else while (c = ++y && c && c[b] || (l = y = 0) || w.pop())if ((e ? c.nodeName.toLowerCase() === g : 1 === c.nodeType) && ++l && (d && ((c[f] || (c[f] = {}))[n] = [a, l]), c === t))break;
                                return l -= u, l === r || l % r == 0 && l / r >= 0
                            }
                        }
                    }, PSEUDO: function (n, i) {
                        var e, r = t.pseudos[n] || t.setFilters[n.toLowerCase()] || u.error("unsupported pseudo: " + n);
                        return r[f] ? r(i) : r.length > 1 ? (e = [n, n, "", i], t.setFilters.hasOwnProperty(n.toLowerCase()) ? h(function (n, t) {
                            for (var u, f = r(n, i), e = f.length; e--;)u = nt.call(n, f[e]), n[u] = !(t[u] = f[e])
                        }) : function (n) {
                            return r(n, 0, e)
                        }) : r
                    }
                },
                pseudos: {
                    not: h(function (n) {
                        var i = [], r = [], t = wt(n.replace(lt, "$1"));
                        return t[f] ? h(function (n, i, r, u) {
                            for (var e, o = t(n, null, u, []), f = n.length; f--;)(e = o[f]) && (n[f] = !(i[f] = e))
                        }) : function (n, u, f) {
                            return i[0] = n, t(i, null, f, r), !r.pop()
                        }
                    }), has: h(function (n) {
                        return function (t) {
                            return u(n, t).length > 0
                        }
                    }), contains: h(function (n) {
                        return function (t) {
                            return (t.textContent || t.innerText || st(t)).indexOf(n) > -1
                        }
                    }), lang: h(function (n) {
                        return ur.test(n || "") || u.error("unsupported lang: " + n), n = n.replace(k, d).toLowerCase(), function (t) {
                            var i;
                            do if (i = l ? t.lang : t.getAttribute("xml:lang") || t.getAttribute("lang"))return i = i.toLowerCase(), i === n || 0 === i.indexOf(n + "-"); while ((t = t.parentNode) && 1 === t.nodeType);
                            return !1
                        }
                    }), target: function (t) {
                        var i = n.location && n.location.hash;
                        return i && i.slice(1) === t.id
                    }, root: function (n) {
                        return n === v
                    }, focus: function (n) {
                        return n === e.activeElement && (!e.hasFocus || e.hasFocus()) && !!(n.type || n.href || ~n.tabIndex)
                    }, enabled: function (n) {
                        return n.disabled === !1
                    }, disabled: function (n) {
                        return n.disabled === !0
                    }, checked: function (n) {
                        var t = n.nodeName.toLowerCase();
                        return "input" === t && !!n.checked || "option" === t && !!n.selected
                    }, selected: function (n) {
                        return n.parentNode && n.parentNode.selectedIndex, n.selected === !0
                    }, empty: function (n) {
                        for (n = n.firstChild; n; n = n.nextSibling)if (n.nodeType < 6)return !1;
                        return !0
                    }, parent: function (n) {
                        return !t.pseudos.empty(n)
                    }, header: function (n) {
                        return er.test(n.nodeName)
                    }, input: function (n) {
                        return fr.test(n.nodeName)
                    }, button: function (n) {
                        var t = n.nodeName.toLowerCase();
                        return "input" === t && "button" === n.type || "button" === t
                    }, text: function (n) {
                        var t;
                        return "input" === n.nodeName.toLowerCase() && "text" === n.type && (null == (t = n.getAttribute("type")) || "text" === t.toLowerCase())
                    }, first: tt(function () {
                        return [0]
                    }), last: tt(function (n, t) {
                        return [t - 1]
                    }), eq: tt(function (n, t, i) {
                        return [0 > i ? i + t : i]
                    }), even: tt(function (n, t) {
                        for (var i = 0; t > i; i += 2)n.push(i);
                        return n
                    }), odd: tt(function (n, t) {
                        for (var i = 1; t > i; i += 2)n.push(i);
                        return n
                    }), lt: tt(function (n, t, i) {
                        for (var r = 0 > i ? i + t : i; --r >= 0;)n.push(r);
                        return n
                    }), gt: tt(function (n, t, i) {
                        for (var r = 0 > i ? i + t : i; ++r < t;)n.push(r);
                        return n
                    })
                }
            };
            t.pseudos.nth = t.pseudos.eq;
            for (it in{radio: !0, checkbox: !0, file: !0, password: !0, image: !0})t.pseudos[it] = hr(it);
            for (it in{submit: !0, reset: !0})t.pseudos[it] = cr(it);
            return wi.prototype = t.filters = t.pseudos, t.setFilters = new wi, wt = u.compile = function (n, t) {
                var r, u = [], e = [], i = ci[n + " "];
                if (!i) {
                    for (t || (t = vt(n)), r = t.length; r--;)i = ei(t[r]), i[f] ? u.push(i) : e.push(i);
                    i = ci(n, lr(e, u))
                }
                return i
            }, r.sortStable = f.split("").sort(bt).join("") === f, r.detectDuplicates = !!rt, p(), r.sortDetached = c(function (n) {
                return 1 & n.compareDocumentPosition(e.createElement("div"))
            }), c(function (n) {
                return n.innerHTML = "<a href='#'><\/a>", "#" === n.firstChild.getAttribute("href")
            }) || ti("type|href|height|width", function (n, t, i) {
                if (!i)return n.getAttribute(t, "type" === t.toLowerCase() ? 1 : 2)
            }), r.attributes && c(function (n) {
                return n.innerHTML = "<input/>", n.firstChild.setAttribute("value", ""), "" === n.firstChild.getAttribute("value")
            }) || ti("value", function (n, t, i) {
                if (!i && "input" === n.nodeName.toLowerCase())return n.defaultValue
            }), c(function (n) {
                return null == n.getAttribute("disabled")
            }) || ti(kt, function (n, t, i) {
                var r;
                if (!i)return n[t] === !0 ? t.toLowerCase() : (r = n.getAttributeNode(t)) && r.specified ? r.value : null
            }), u
        }(n);
        i.find = y;
        i.expr = y.selectors;
        i.expr[":"] = i.expr.pseudos;
        i.unique = y.uniqueSort;
        i.text = y.getText;
        i.isXMLDoc = y.isXML;
        i.contains = y.contains;
        var ki = i.expr.match.needsContext, di = /^<(\w+)\s*\/?>(?:<\/\1>|)$/, ef = /^.[^:#\[\.,]*$/;
        i.filter = function (n, t, r) {
            var u = t[0];
            return r && (n = ":not(" + n + ")"), 1 === t.length && 1 === u.nodeType ? i.find.matchesSelector(u, n) ? [u] : [] : i.find.matches(n, i.grep(t, function (n) {
                return 1 === n.nodeType
            }))
        };
        i.fn.extend({
            find: function (n) {
                var t, u = this.length, r = [], f = this;
                if ("string" != typeof n)return this.pushStack(i(n).filter(function () {
                    for (t = 0; u > t; t++)if (i.contains(f[t], this))return !0
                }));
                for (t = 0; u > t; t++)i.find(n, f[t], r);
                return r = this.pushStack(u > 1 ? i.unique(r) : r), r.selector = this.selector ? this.selector + " " + n : n, r
            }, filter: function (n) {
                return this.pushStack(fi(this, n || [], !1))
            }, not: function (n) {
                return this.pushStack(fi(this, n || [], !0))
            }, is: function (n) {
                return !!fi(this, "string" == typeof n && ki.test(n) ? i(n) : n || [], !1).length
            }
        });
        gi = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/;
        nr = i.fn.init = function (n, t) {
            var r, f;
            if (!n)return this;
            if ("string" == typeof n) {
                if (r = "<" === n[0] && ">" === n[n.length - 1] && n.length >= 3 ? [null, n, null] : gi.exec(n), !r || !r[1] && t)return !t || t.jquery ? (t || st).find(n) : this.constructor(t).find(n);
                if (r[1]) {
                    if (t = t instanceof i ? t[0] : t, i.merge(this, i.parseHTML(r[1], t && t.nodeType ? t.ownerDocument || t : u, !0)), di.test(r[1]) && i.isPlainObject(t))for (r in t)i.isFunction(this[r]) ? this[r](t[r]) : this.attr(r, t[r]);
                    return this
                }
                return f = u.getElementById(r[2]), f && f.parentNode && (this.length = 1, this[0] = f), this.context = u, this.selector = n, this
            }
            return n.nodeType ? (this.context = this[0] = n, this.length = 1, this) : i.isFunction(n) ? "undefined" != typeof st.ready ? st.ready(n) : n(i) : (void 0 !== n.selector && (this.selector = n.selector, this.context = n.context), i.makeArray(n, this))
        };
        nr.prototype = i.fn;
        st = i(u);
        tr = /^(?:parents|prev(?:Until|All))/;
        ir = {children: !0, contents: !0, next: !0, prev: !0};
        i.extend({
            dir: function (n, t, r) {
                for (var u = [], f = void 0 !== r; (n = n[t]) && 9 !== n.nodeType;)if (1 === n.nodeType) {
                    if (f && i(n).is(r))break;
                    u.push(n)
                }
                return u
            }, sibling: function (n, t) {
                for (var i = []; n; n = n.nextSibling)1 === n.nodeType && n !== t && i.push(n);
                return i
            }
        });
        i.fn.extend({
            has: function (n) {
                var t = i(n, this), r = t.length;
                return this.filter(function () {
                    for (var n = 0; r > n; n++)if (i.contains(this, t[n]))return !0
                })
            }, closest: function (n, t) {
                for (var r, f = 0, o = this.length, u = [], e = ki.test(n) || "string" != typeof n ? i(n, t || this.context) : 0; o > f; f++)for (r = this[f]; r && r !== t; r = r.parentNode)if (r.nodeType < 11 && (e ? e.index(r) > -1 : 1 === r.nodeType && i.find.matchesSelector(r, n))) {
                    u.push(r);
                    break
                }
                return this.pushStack(u.length > 1 ? i.unique(u) : u)
            }, index: function (n) {
                return n ? "string" == typeof n ? et.call(i(n), this[0]) : et.call(this, n.jquery ? n[0] : n) : this[0] && this[0].parentNode ? this.first().prevAll().length : -1
            }, add: function (n, t) {
                return this.pushStack(i.unique(i.merge(this.get(), i(n, t))))
            }, addBack: function (n) {
                return this.add(null == n ? this.prevObject : this.prevObject.filter(n))
            }
        });
        i.each({
            parent: function (n) {
                var t = n.parentNode;
                return t && 11 !== t.nodeType ? t : null
            }, parents: function (n) {
                return i.dir(n, "parentNode")
            }, parentsUntil: function (n, t, r) {
                return i.dir(n, "parentNode", r)
            }, next: function (n) {
                return rr(n, "nextSibling")
            }, prev: function (n) {
                return rr(n, "previousSibling")
            }, nextAll: function (n) {
                return i.dir(n, "nextSibling")
            }, prevAll: function (n) {
                return i.dir(n, "previousSibling")
            }, nextUntil: function (n, t, r) {
                return i.dir(n, "nextSibling", r)
            }, prevUntil: function (n, t, r) {
                return i.dir(n, "previousSibling", r)
            }, siblings: function (n) {
                return i.sibling((n.parentNode || {}).firstChild, n)
            }, children: function (n) {
                return i.sibling(n.firstChild)
            }, contents: function (n) {
                return n.contentDocument || i.merge([], n.childNodes)
            }
        }, function (n, t) {
            i.fn[n] = function (r, u) {
                var f = i.map(this, t, r);
                return "Until" !== n.slice(-5) && (u = r), u && "string" == typeof u && (f = i.filter(u, f)), this.length > 1 && (ir[n] || i.unique(f), tr.test(n) && f.reverse()), this.pushStack(f)
            }
        });
        c = /\S+/g;
        ei = {};
        i.Callbacks = function (n) {
            n = "string" == typeof n ? ei[n] || of(n) : i.extend({}, n);
            var u, h, o, c, f, e, t = [], r = !n.once && [], l = function (i) {
                for (u = n.memory && i, h = !0, e = c || 0, c = 0, f = t.length, o = !0; t && f > e; e++)if (t[e].apply(i[0], i[1]) === !1 && n.stopOnFalse) {
                    u = !1;
                    break
                }
                o = !1;
                t && (r ? r.length && l(r.shift()) : u ? t = [] : s.disable())
            }, s = {
                add: function () {
                    if (t) {
                        var r = t.length;
                        !function e(r) {
                            i.each(r, function (r, u) {
                                var f = i.type(u);
                                "function" === f ? n.unique && s.has(u) || t.push(u) : u && u.length && "string" !== f && e(u)
                            })
                        }(arguments);
                        o ? f = t.length : u && (c = r, l(u))
                    }
                    return this
                }, remove: function () {
                    return t && i.each(arguments, function (n, r) {
                        for (var u; (u = i.inArray(r, t, u)) > -1;)t.splice(u, 1), o && (f >= u && f--, e >= u && e--)
                    }), this
                }, has: function (n) {
                    return n ? i.inArray(n, t) > -1 : !(!t || !t.length)
                }, empty: function () {
                    return t = [], f = 0, this
                }, disable: function () {
                    return t = r = u = void 0, this
                }, disabled: function () {
                    return !t
                }, lock: function () {
                    return r = void 0, u || s.disable(), this
                }, locked: function () {
                    return !r
                }, fireWith: function (n, i) {
                    return !t || h && !r || (i = i || [], i = [n, i.slice ? i.slice() : i], o ? r.push(i) : l(i)), this
                }, fire: function () {
                    return s.fireWith(this, arguments), this
                }, fired: function () {
                    return !!h
                }
            };
            return s
        };
        i.extend({
            Deferred: function (n) {
                var u = [["resolve", "done", i.Callbacks("once memory"), "resolved"], ["reject", "fail", i.Callbacks("once memory"), "rejected"], ["notify", "progress", i.Callbacks("memory")]], f = "pending", r = {
                    state: function () {
                        return f
                    }, always: function () {
                        return t.done(arguments).fail(arguments), this
                    }, then: function () {
                        var n = arguments;
                        return i.Deferred(function (f) {
                            i.each(u, function (u, e) {
                                var o = i.isFunction(n[u]) && n[u];
                                t[e[1]](function () {
                                    var n = o && o.apply(this, arguments);
                                    n && i.isFunction(n.promise) ? n.promise().done(f.resolve).fail(f.reject).progress(f.notify) : f[e[0] + "With"](this === r ? f.promise() : this, o ? [n] : arguments)
                                })
                            });
                            n = null
                        }).promise()
                    }, promise: function (n) {
                        return null != n ? i.extend(n, r) : r
                    }
                }, t = {};
                return r.pipe = r.then, i.each(u, function (n, i) {
                    var e = i[2], o = i[3];
                    r[i[1]] = e.add;
                    o && e.add(function () {
                        f = o
                    }, u[1 ^ n][2].disable, u[2][2].lock);
                    t[i[0]] = function () {
                        return t[i[0] + "With"](this === t ? r : this, arguments), this
                    };
                    t[i[0] + "With"] = e.fireWith
                }), r.promise(t), n && n.call(t, t), t
            }, when: function (n) {
                var t = 0, u = a.call(arguments), r = u.length, e = 1 !== r || n && i.isFunction(n.promise) ? r : 0, f = 1 === e ? n : i.Deferred(), h = function (n, t, i) {
                    return function (r) {
                        t[n] = this;
                        i[n] = arguments.length > 1 ? a.call(arguments) : r;
                        i === o ? f.notifyWith(t, i) : --e || f.resolveWith(t, i)
                    }
                }, o, c, s;
                if (r > 1)for (o = new Array(r), c = new Array(r), s = new Array(r); r > t; t++)u[t] && i.isFunction(u[t].promise) ? u[t].promise().done(h(t, s, u)).fail(f.reject).progress(h(t, c, o)) : --e;
                return e || f.resolveWith(s, u), f.promise()
            }
        });
        i.fn.ready = function (n) {
            return i.ready.promise().done(n), this
        };
        i.extend({
            isReady: !1, readyWait: 1, holdReady: function (n) {
                n ? i.readyWait++ : i.ready(!0)
            }, ready: function (n) {
                (n === !0 ? --i.readyWait : i.isReady) || (i.isReady = !0, n !== !0 && --i.readyWait > 0 || (ht.resolveWith(u, [i]), i.fn.trigger && i(u).trigger("ready").off("ready")))
            }
        });
        i.ready.promise = function (t) {
            return ht || (ht = i.Deferred(), "complete" === u.readyState ? setTimeout(i.ready) : (u.addEventListener("DOMContentLoaded", ct, !1), n.addEventListener("load", ct, !1))), ht.promise(t)
        };
        i.ready.promise();
        l = i.access = function (n, t, r, u, f, e, o) {
            var s = 0, c = n.length, h = null == r;
            if ("object" === i.type(r)) {
                f = !0;
                for (s in r)i.access(n, t, s, r[s], !0, e, o)
            } else if (void 0 !== u && (f = !0, i.isFunction(u) || (o = !0), h && (o ? (t.call(n, u), t = null) : (h = t, t = function (n, t, r) {
                    return h.call(i(n), r)
                })), t))for (; c > s; s++)t(n[s], r, o ? u : u.call(n[s], s, t(n[s], r)));
            return f ? n : h ? t.call(n) : c ? t(n[0], r) : e
        };
        i.acceptData = function (n) {
            return 1 === n.nodeType || 9 === n.nodeType || !+n.nodeType
        };
        p.uid = 1;
        p.accepts = i.acceptData;
        p.prototype = {
            key: function (n) {
                if (!p.accepts(n))return 0;
                var r = {}, t = n[this.expando];
                if (!t) {
                    t = p.uid++;
                    try {
                        r[this.expando] = {value: t};
                        Object.defineProperties(n, r)
                    } catch (u) {
                        r[this.expando] = t;
                        i.extend(n, r)
                    }
                }
                return this.cache[t] || (this.cache[t] = {}), t
            }, set: function (n, t, r) {
                var f, e = this.key(n), u = this.cache[e];
                if ("string" == typeof t)u[t] = r; else if (i.isEmptyObject(u))i.extend(this.cache[e], t); else for (f in t)u[f] = t[f];
                return u
            }, get: function (n, t) {
                var i = this.cache[this.key(n)];
                return void 0 === t ? i : i[t]
            }, access: function (n, t, r) {
                var u;
                return void 0 === t || t && "string" == typeof t && void 0 === r ? (u = this.get(n, t), void 0 !== u ? u : this.get(n, i.camelCase(t))) : (this.set(n, t, r), void 0 !== r ? r : t)
            }, remove: function (n, t) {
                var u, r, f, o = this.key(n), e = this.cache[o];
                if (void 0 === t)this.cache[o] = {}; else for (i.isArray(t) ? r = t.concat(t.map(i.camelCase)) : (f = i.camelCase(t), (t in e) ? r = [t, f] : (r = f, r = (r in e) ? [r] : r.match(c) || [])), u = r.length; u--;)delete e[r[u]]
            }, hasData: function (n) {
                return !i.isEmptyObject(this.cache[n[this.expando]] || {})
            }, discard: function (n) {
                n[this.expando] && delete this.cache[n[this.expando]]
            }
        };
        var r = new p, e = new p, sf = /^(?:\{[\w\W]*\}|\[[\w\W]*\])$/, hf = /([A-Z])/g;
        i.extend({
            hasData: function (n) {
                return e.hasData(n) || r.hasData(n)
            }, data: function (n, t, i) {
                return e.access(n, t, i)
            }, removeData: function (n, t) {
                e.remove(n, t)
            }, _data: function (n, t, i) {
                return r.access(n, t, i)
            }, _removeData: function (n, t) {
                r.remove(n, t)
            }
        });
        i.fn.extend({
            data: function (n, t) {
                var o, f, s, u = this[0], h = u && u.attributes;
                if (void 0 === n) {
                    if (this.length && (s = e.get(u), 1 === u.nodeType && !r.get(u, "hasDataAttrs"))) {
                        for (o = h.length; o--;)f = h[o].name, 0 === f.indexOf("data-") && (f = i.camelCase(f.slice(5)), ur(u, f, s[f]));
                        r.set(u, "hasDataAttrs", !0)
                    }
                    return s
                }
                return "object" == typeof n ? this.each(function () {
                    e.set(this, n)
                }) : l(this, function (t) {
                    var r, f = i.camelCase(n);
                    if (u && void 0 === t) {
                        if ((r = e.get(u, n), void 0 !== r) || (r = e.get(u, f), void 0 !== r) || (r = ur(u, f, void 0), void 0 !== r))return r
                    } else this.each(function () {
                        var i = e.get(this, f);
                        e.set(this, f, t);
                        -1 !== n.indexOf("-") && void 0 !== i && e.set(this, n, t)
                    })
                }, null, t, arguments.length > 1, null, !0)
            }, removeData: function (n) {
                return this.each(function () {
                    e.remove(this, n)
                })
            }
        });
        i.extend({
            queue: function (n, t, u) {
                var f;
                if (n)return (t = (t || "fx") + "queue", f = r.get(n, t), u && (!f || i.isArray(u) ? f = r.access(n, t, i.makeArray(u)) : f.push(u)), f || [])
            }, dequeue: function (n, t) {
                t = t || "fx";
                var r = i.queue(n, t), e = r.length, u = r.shift(), f = i._queueHooks(n, t), o = function () {
                    i.dequeue(n, t)
                };
                "inprogress" === u && (u = r.shift(), e--);
                u && ("fx" === t && r.unshift("inprogress"), delete f.stop, u.call(n, o, f));
                !e && f && f.empty.fire()
            }, _queueHooks: function (n, t) {
                var u = t + "queueHooks";
                return r.get(n, u) || r.access(n, u, {
                        empty: i.Callbacks("once memory").add(function () {
                            r.remove(n, [t + "queue", u])
                        })
                    })
            }
        });
        i.fn.extend({
            queue: function (n, t) {
                var r = 2;
                return "string" != typeof n && (t = n, n = "fx", r--), arguments.length < r ? i.queue(this[0], n) : void 0 === t ? this : this.each(function () {
                    var r = i.queue(this, n, t);
                    i._queueHooks(this, n);
                    "fx" === n && "inprogress" !== r[0] && i.dequeue(this, n)
                })
            }, dequeue: function (n) {
                return this.each(function () {
                    i.dequeue(this, n)
                })
            }, clearQueue: function (n) {
                return this.queue(n || "fx", [])
            }, promise: function (n, t) {
                var u, e = 1, o = i.Deferred(), f = this, s = this.length, h = function () {
                    --e || o.resolveWith(f, [f])
                };
                for ("string" != typeof n && (t = n, n = void 0), n = n || "fx"; s--;)u = r.get(f[s], n + "queueHooks"), u && u.empty && (e++, u.empty.add(h));
                return h(), o.promise(t)
            }
        });
        var lt = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source, w = ["Top", "Right", "Bottom", "Left"], it = function (n, t) {
            return n = t || n, "none" === i.css(n, "display") || !i.contains(n.ownerDocument, n)
        }, fr = /^(?:checkbox|radio)$/i;
        !function () {
            var t = u.createDocumentFragment(), n = t.appendChild(u.createElement("div"));
            n.innerHTML = "<input type='radio' checked='checked' name='t'/>";
            f.checkClone = n.cloneNode(!0).cloneNode(!0).lastChild.checked;
            n.innerHTML = "<textarea>x<\/textarea>";
            f.noCloneChecked = !!n.cloneNode(!0).lastChild.defaultValue
        }();
        d = "undefined";
        f.focusinBubbles = "onfocusin"in n;
        var cf = /^key/, lf = /^(?:mouse|contextmenu)|click/, er = /^(?:focusinfocus|focusoutblur)$/, or = /^([^.]*)(?:\.(.+)|)$/;
        i.event = {
            global: {},
            add: function (n, t, u, f, e) {
                var v, y, w, p, b, h, s, l, o, k, g, a = r.get(n);
                if (a)for (u.handler && (v = u, u = v.handler, e = v.selector), u.guid || (u.guid = i.guid++), (p = a.events) || (p = a.events = {}), (y = a.handle) || (y = a.handle = function (t) {
                    if (typeof i !== d && i.event.triggered !== t.type)return i.event.dispatch.apply(n, arguments)
                }), t = (t || "").match(c) || [""], b = t.length; b--;)w = or.exec(t[b]) || [], o = g = w[1], k = (w[2] || "").split(".").sort(), o && (s = i.event.special[o] || {}, o = (e ? s.delegateType : s.bindType) || o, s = i.event.special[o] || {}, h = i.extend({
                    type: o,
                    origType: g,
                    data: f,
                    handler: u,
                    guid: u.guid,
                    selector: e,
                    needsContext: e && i.expr.match.needsContext.test(e),
                    namespace: k.join(".")
                }, v), (l = p[o]) || (l = p[o] = [], l.delegateCount = 0, s.setup && s.setup.call(n, f, k, y) !== !1 || n.addEventListener && n.addEventListener(o, y, !1)), s.add && (s.add.call(n, h), h.handler.guid || (h.handler.guid = u.guid)), e ? l.splice(l.delegateCount++, 0, h) : l.push(h), i.event.global[o] = !0)
            },
            remove: function (n, t, u, f, e) {
                var p, k, h, v, w, s, l, a, o, b, d, y = r.hasData(n) && r.get(n);
                if (y && (v = y.events)) {
                    for (t = (t || "").match(c) || [""], w = t.length; w--;)if (h = or.exec(t[w]) || [], o = d = h[1], b = (h[2] || "").split(".").sort(), o) {
                        for (l = i.event.special[o] || {}, o = (f ? l.delegateType : l.bindType) || o, a = v[o] || [], h = h[2] && new RegExp("(^|\\.)" + b.join("\\.(?:.*\\.|)") + "(\\.|$)"), k = p = a.length; p--;)s = a[p], !e && d !== s.origType || u && u.guid !== s.guid || h && !h.test(s.namespace) || f && f !== s.selector && ("**" !== f || !s.selector) || (a.splice(p, 1), s.selector && a.delegateCount--, l.remove && l.remove.call(n, s));
                        k && !a.length && (l.teardown && l.teardown.call(n, b, y.handle) !== !1 || i.removeEvent(n, o, y.handle), delete v[o])
                    } else for (o in v)i.event.remove(n, o + t[w], u, f, !0);
                    i.isEmptyObject(v) && (delete y.handle, r.remove(n, "events"))
                }
            },
            trigger: function (t, f, e, o) {
                var w, s, c, b, a, v, l, p = [e || u], h = ri.call(t, "type") ? t.type : t, y = ri.call(t, "namespace") ? t.namespace.split(".") : [];
                if (s = c = e = e || u, 3 !== e.nodeType && 8 !== e.nodeType && !er.test(h + i.event.triggered) && (h.indexOf(".") >= 0 && (y = h.split("."), h = y.shift(), y.sort()), a = h.indexOf(":") < 0 && "on" + h, t = t[i.expando] ? t : new i.Event(h, "object" == typeof t && t), t.isTrigger = o ? 2 : 3, t.namespace = y.join("."), t.namespace_re = t.namespace ? new RegExp("(^|\\.)" + y.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, t.result = void 0, t.target || (t.target = e), f = null == f ? [t] : i.makeArray(f, [t]), l = i.event.special[h] || {}, o || !l.trigger || l.trigger.apply(e, f) !== !1)) {
                    if (!o && !l.noBubble && !i.isWindow(e)) {
                        for (b = l.delegateType || h, er.test(b + h) || (s = s.parentNode); s; s = s.parentNode)p.push(s), c = s;
                        c === (e.ownerDocument || u) && p.push(c.defaultView || c.parentWindow || n)
                    }
                    for (w = 0; (s = p[w++]) && !t.isPropagationStopped();)t.type = w > 1 ? b : l.bindType || h, v = (r.get(s, "events") || {})[t.type] && r.get(s, "handle"), v && v.apply(s, f), v = a && s[a], v && v.apply && i.acceptData(s) && (t.result = v.apply(s, f), t.result === !1 && t.preventDefault());
                    return t.type = h, o || t.isDefaultPrevented() || l._default && l._default.apply(p.pop(), f) !== !1 || !i.acceptData(e) || a && i.isFunction(e[h]) && !i.isWindow(e) && (c = e[a], c && (e[a] = null), i.event.triggered = h, e[h](), i.event.triggered = void 0, c && (e[a] = c)), t.result
                }
            },
            dispatch: function (n) {
                n = i.event.fix(n);
                var o, s, e, u, t, h = [], c = a.call(arguments), l = (r.get(this, "events") || {})[n.type] || [], f = i.event.special[n.type] || {};
                if (c[0] = n, n.delegateTarget = this, !f.preDispatch || f.preDispatch.call(this, n) !== !1) {
                    for (h = i.event.handlers.call(this, n, l), o = 0; (u = h[o++]) && !n.isPropagationStopped();)for (n.currentTarget = u.elem, s = 0; (t = u.handlers[s++]) && !n.isImmediatePropagationStopped();)(!n.namespace_re || n.namespace_re.test(t.namespace)) && (n.handleObj = t, n.data = t.data, e = ((i.event.special[t.origType] || {}).handle || t.handler).apply(u.elem, c), void 0 !== e && (n.result = e) === !1 && (n.preventDefault(), n.stopPropagation()));
                    return f.postDispatch && f.postDispatch.call(this, n), n.result
                }
            },
            handlers: function (n, t) {
                var e, u, f, o, h = [], s = t.delegateCount, r = n.target;
                if (s && r.nodeType && (!n.button || "click" !== n.type))for (; r !== this; r = r.parentNode || this)if (r.disabled !== !0 || "click" !== n.type) {
                    for (u = [], e = 0; s > e; e++)o = t[e], f = o.selector + " ", void 0 === u[f] && (u[f] = o.needsContext ? i(f, this).index(r) >= 0 : i.find(f, this, null, [r]).length), u[f] && u.push(o);
                    u.length && h.push({elem: r, handlers: u})
                }
                return s < t.length && h.push({elem: this, handlers: t.slice(s)}), h
            },
            props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
            fixHooks: {},
            keyHooks: {
                props: "char charCode key keyCode".split(" "), filter: function (n, t) {
                    return null == n.which && (n.which = null != t.charCode ? t.charCode : t.keyCode), n
                }
            },
            mouseHooks: {
                props: "button buttons clientX clientY offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
                filter: function (n, t) {
                    var e, i, r, f = t.button;
                    return null == n.pageX && null != t.clientX && (e = n.target.ownerDocument || u, i = e.documentElement, r = e.body, n.pageX = t.clientX + (i && i.scrollLeft || r && r.scrollLeft || 0) - (i && i.clientLeft || r && r.clientLeft || 0), n.pageY = t.clientY + (i && i.scrollTop || r && r.scrollTop || 0) - (i && i.clientTop || r && r.clientTop || 0)), n.which || void 0 === f || (n.which = 1 & f ? 1 : 2 & f ? 3 : 4 & f ? 2 : 0), n
                }
            },
            fix: function (n) {
                if (n[i.expando])return n;
                var f, e, o, r = n.type, s = n, t = this.fixHooks[r];
                for (t || (this.fixHooks[r] = t = lf.test(r) ? this.mouseHooks : cf.test(r) ? this.keyHooks : {}), o = t.props ? this.props.concat(t.props) : this.props, n = new i.Event(s), f = o.length; f--;)e = o[f], n[e] = s[e];
                return n.target || (n.target = u), 3 === n.target.nodeType && (n.target = n.target.parentNode), t.filter ? t.filter(n, s) : n
            },
            special: {
                load: {noBubble: !0}, focus: {
                    trigger: function () {
                        if (this !== sr() && this.focus)return (this.focus(), !1)
                    }, delegateType: "focusin"
                }, blur: {
                    trigger: function () {
                        if (this === sr() && this.blur)return (this.blur(), !1)
                    }, delegateType: "focusout"
                }, click: {
                    trigger: function () {
                        if ("checkbox" === this.type && this.click && i.nodeName(this, "input"))return (this.click(), !1)
                    }, _default: function (n) {
                        return i.nodeName(n.target, "a")
                    }
                }, beforeunload: {
                    postDispatch: function (n) {
                        void 0 !== n.result && (n.originalEvent.returnValue = n.result)
                    }
                }
            },
            simulate: function (n, t, r, u) {
                var f = i.extend(new i.Event, r, {type: n, isSimulated: !0, originalEvent: {}});
                u ? i.event.trigger(f, null, t) : i.event.dispatch.call(t, f);
                f.isDefaultPrevented() && r.preventDefault()
            }
        };
        i.removeEvent = function (n, t, i) {
            n.removeEventListener && n.removeEventListener(t, i, !1)
        };
        i.Event = function (n, t) {
            return this instanceof i.Event ? (n && n.type ? (this.originalEvent = n, this.type = n.type, this.isDefaultPrevented = n.defaultPrevented || void 0 === n.defaultPrevented && n.getPreventDefault && n.getPreventDefault() ? at : g) : this.type = n, t && i.extend(this, t), this.timeStamp = n && n.timeStamp || i.now(), void(this[i.expando] = !0)) : new i.Event(n, t)
        };
        i.Event.prototype = {
            isDefaultPrevented: g,
            isPropagationStopped: g,
            isImmediatePropagationStopped: g,
            preventDefault: function () {
                var n = this.originalEvent;
                this.isDefaultPrevented = at;
                n && n.preventDefault && n.preventDefault()
            },
            stopPropagation: function () {
                var n = this.originalEvent;
                this.isPropagationStopped = at;
                n && n.stopPropagation && n.stopPropagation()
            },
            stopImmediatePropagation: function () {
                this.isImmediatePropagationStopped = at;
                this.stopPropagation()
            }
        };
        i.each({mouseenter: "mouseover", mouseleave: "mouseout"}, function (n, t) {
            i.event.special[n] = {
                delegateType: t, bindType: t, handle: function (n) {
                    var u, f = this, r = n.relatedTarget, e = n.handleObj;
                    return (!r || r !== f && !i.contains(f, r)) && (n.type = e.origType, u = e.handler.apply(this, arguments), n.type = t), u
                }
            }
        });
        f.focusinBubbles || i.each({focus: "focusin", blur: "focusout"}, function (n, t) {
            var u = function (n) {
                i.event.simulate(t, n.target, i.event.fix(n), !0)
            };
            i.event.special[t] = {
                setup: function () {
                    var i = this.ownerDocument || this, f = r.access(i, t);
                    f || i.addEventListener(n, u, !0);
                    r.access(i, t, (f || 0) + 1)
                }, teardown: function () {
                    var i = this.ownerDocument || this, f = r.access(i, t) - 1;
                    f ? r.access(i, t, f) : (i.removeEventListener(n, u, !0), r.remove(i, t))
                }
            }
        });
        i.fn.extend({
            on: function (n, t, r, u, f) {
                var e, o;
                if ("object" == typeof n) {
                    "string" != typeof t && (r = r || t, t = void 0);
                    for (o in n)this.on(o, t, r, n[o], f);
                    return this
                }
                if (null == r && null == u ? (u = t, r = t = void 0) : null == u && ("string" == typeof t ? (u = r, r = void 0) : (u = r, r = t, t = void 0)), u === !1)u = g; else if (!u)return this;
                return 1 === f && (e = u, u = function (n) {
                    return i().off(n), e.apply(this, arguments)
                }, u.guid = e.guid || (e.guid = i.guid++)), this.each(function () {
                    i.event.add(this, n, u, r, t)
                })
            }, one: function (n, t, i, r) {
                return this.on(n, t, i, r, 1)
            }, off: function (n, t, r) {
                var u, f;
                if (n && n.preventDefault && n.handleObj)return u = n.handleObj, i(n.delegateTarget).off(u.namespace ? u.origType + "." + u.namespace : u.origType, u.selector, u.handler), this;
                if ("object" == typeof n) {
                    for (f in n)this.off(f, t, n[f]);
                    return this
                }
                return (t === !1 || "function" == typeof t) && (r = t, t = void 0), r === !1 && (r = g), this.each(function () {
                    i.event.remove(this, n, r, t)
                })
            }, trigger: function (n, t) {
                return this.each(function () {
                    i.event.trigger(n, t, this)
                })
            }, triggerHandler: function (n, t) {
                var r = this[0];
                if (r)return i.event.trigger(n, t, r, !0)
            }
        });
        var hr = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi, cr = /<([\w:]+)/, af = /<|&#?\w+;/, vf = /<(?:script|style|link)/i, yf = /checked\s*(?:[^=]|=\s*.checked.)/i, lr = /^$|\/(?:java|ecma)script/i, pf = /^true\/(.*)/, wf = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g, h = {
            option: [1, "<select multiple='multiple'>", "<\/select>"],
            thead: [1, "<table>", "<\/table>"],
            col: [2, "<table><colgroup>", "<\/colgroup><\/table>"],
            tr: [2, "<table><tbody>", "<\/tbody><\/table>"],
            td: [3, "<table><tbody><tr>", "<\/tr><\/tbody><\/table>"],
            _default: [0, "", ""]
        };
        h.optgroup = h.option;
        h.tbody = h.tfoot = h.colgroup = h.caption = h.thead;
        h.th = h.td;
        i.extend({
            clone: function (n, t, r) {
                var u, c, s, e, h = n.cloneNode(!0), l = i.contains(n.ownerDocument, n);
                if (!(f.noCloneChecked || 1 !== n.nodeType && 11 !== n.nodeType || i.isXMLDoc(n)))for (e = o(h), s = o(n), u = 0, c = s.length; c > u; u++)df(s[u], e[u]);
                if (t)if (r)for (s = s || o(n), e = e || o(h), u = 0, c = s.length; c > u; u++)vr(s[u], e[u]); else vr(n, h);
                return e = o(h, "script"), e.length > 0 && oi(e, !l && o(n, "script")), h
            }, buildFragment: function (n, t, r, u) {
                for (var f, e, y, l, p, a, s = t.createDocumentFragment(), v = [], c = 0, w = n.length; w > c; c++)if (f = n[c], f || 0 === f)if ("object" === i.type(f))i.merge(v, f.nodeType ? [f] : f); else if (af.test(f)) {
                    for (e = e || s.appendChild(t.createElement("div")), y = (cr.exec(f) || ["", ""])[1].toLowerCase(), l = h[y] || h._default, e.innerHTML = l[1] + f.replace(hr, "<$1><\/$2>") + l[2], a = l[0]; a--;)e = e.lastChild;
                    i.merge(v, e.childNodes);
                    e = s.firstChild;
                    e.textContent = ""
                } else v.push(t.createTextNode(f));
                for (s.textContent = "", c = 0; f = v[c++];)if ((!u || -1 === i.inArray(f, u)) && (p = i.contains(f.ownerDocument, f), e = o(s.appendChild(f), "script"), p && oi(e), r))for (a = 0; f = e[a++];)lr.test(f.type || "") && r.push(f);
                return s
            }, cleanData: function (n) {
                for (var o, t, s, f, u, h, l = i.event.special, c = 0; void 0 !== (t = n[c]); c++) {
                    if (i.acceptData(t) && (u = t[r.expando], u && (o = r.cache[u]))) {
                        if (s = Object.keys(o.events || {}), s.length)for (h = 0; void 0 !== (f = s[h]); h++)l[f] ? i.event.remove(t, f) : i.removeEvent(t, f, o.handle);
                        r.cache[u] && delete r.cache[u]
                    }
                    delete e.cache[t[e.expando]]
                }
            }
        });
        i.fn.extend({
            text: function (n) {
                return l(this, function (n) {
                    return void 0 === n ? i.text(this) : this.empty().each(function () {
                        (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) && (this.textContent = n)
                    })
                }, null, n, arguments.length)
            }, append: function () {
                return this.domManip(arguments, function (n) {
                    if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                        var t = ar(this, n);
                        t.appendChild(n)
                    }
                })
            }, prepend: function () {
                return this.domManip(arguments, function (n) {
                    if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                        var t = ar(this, n);
                        t.insertBefore(n, t.firstChild)
                    }
                })
            }, before: function () {
                return this.domManip(arguments, function (n) {
                    this.parentNode && this.parentNode.insertBefore(n, this)
                })
            }, after: function () {
                return this.domManip(arguments, function (n) {
                    this.parentNode && this.parentNode.insertBefore(n, this.nextSibling)
                })
            }, remove: function (n, t) {
                for (var r, f = n ? i.filter(n, this) : this, u = 0; null != (r = f[u]); u++)t || 1 !== r.nodeType || i.cleanData(o(r)), r.parentNode && (t && i.contains(r.ownerDocument, r) && oi(o(r, "script")), r.parentNode.removeChild(r));
                return this
            }, empty: function () {
                for (var n, t = 0; null != (n = this[t]); t++)1 === n.nodeType && (i.cleanData(o(n, !1)), n.textContent = "");
                return this
            }, clone: function (n, t) {
                return n = null == n ? !1 : n, t = null == t ? n : t, this.map(function () {
                    return i.clone(this, n, t)
                })
            }, html: function (n) {
                return l(this, function (n) {
                    var t = this[0] || {}, r = 0, u = this.length;
                    if (void 0 === n && 1 === t.nodeType)return t.innerHTML;
                    if ("string" == typeof n && !vf.test(n) && !h[(cr.exec(n) || ["", ""])[1].toLowerCase()]) {
                        n = n.replace(hr, "<$1><\/$2>");
                        try {
                            for (; u > r; r++)t = this[r] || {}, 1 === t.nodeType && (i.cleanData(o(t, !1)), t.innerHTML = n);
                            t = 0
                        } catch (f) {
                        }
                    }
                    t && this.empty().append(n)
                }, null, n, arguments.length)
            }, replaceWith: function () {
                var n = arguments[0];
                return this.domManip(arguments, function (t) {
                    n = this.parentNode;
                    i.cleanData(o(this));
                    n && n.replaceChild(t, this)
                }), n && (n.length || n.nodeType) ? this : this.remove()
            }, detach: function (n) {
                return this.remove(n, !0)
            }, domManip: function (n, t) {
                n = wi.apply([], n);
                var h, v, s, c, u, y, e = 0, l = this.length, w = this, b = l - 1, a = n[0], p = i.isFunction(a);
                if (p || l > 1 && "string" == typeof a && !f.checkClone && yf.test(a))return this.each(function (i) {
                    var r = w.eq(i);
                    p && (n[0] = a.call(this, i, r.html()));
                    r.domManip(n, t)
                });
                if (l && (h = i.buildFragment(n, this[0].ownerDocument, !1, this), v = h.firstChild, 1 === h.childNodes.length && (h = v), v)) {
                    for (s = i.map(o(h, "script"), bf), c = s.length; l > e; e++)u = h, e !== b && (u = i.clone(u, !0, !0), c && i.merge(s, o(u, "script"))), t.call(this[e], u, e);
                    if (c)for (y = s[s.length - 1].ownerDocument, i.map(s, kf), e = 0; c > e; e++)u = s[e], lr.test(u.type || "") && !r.access(u, "globalEval") && i.contains(y, u) && (u.src ? i._evalUrl && i._evalUrl(u.src) : i.globalEval(u.textContent.replace(wf, "")))
                }
                return this
            }
        });
        i.each({
            appendTo: "append",
            prependTo: "prepend",
            insertBefore: "before",
            insertAfter: "after",
            replaceAll: "replaceWith"
        }, function (n, t) {
            i.fn[n] = function (n) {
                for (var u, f = [], e = i(n), o = e.length - 1, r = 0; o >= r; r++)u = r === o ? this : this.clone(!0), i(e[r])[t](u), ii.apply(f, u.get());
                return this.pushStack(f)
            }
        });
        si = {};
        var wr = /^margin/, hi = new RegExp("^(" + lt + ")(?!px)[a-z%]+$", "i"), yt = function (n) {
            return n.ownerDocument.defaultView.getComputedStyle(n, null)
        };
        !function () {
            function h() {
                t.style.cssText = "-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;padding:1px;border:1px;display:block;width:4px;margin-top:1%;position:absolute;top:1%";
                e.appendChild(r);
                var i = n.getComputedStyle(t, null);
                s = "1%" !== i.top;
                o = "4px" === i.width;
                e.removeChild(r)
            }

            var s, o, c = "padding:0;margin:0;border:0;display:block;-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box", e = u.documentElement, r = u.createElement("div"), t = u.createElement("div");
            t.style.backgroundClip = "content-box";
            t.cloneNode(!0).style.backgroundClip = "";
            f.clearCloneStyle = "content-box" === t.style.backgroundClip;
            r.style.cssText = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px;margin-top:1px";
            r.appendChild(t);
            n.getComputedStyle && i.extend(f, {
                pixelPosition: function () {
                    return h(), s
                }, boxSizingReliable: function () {
                    return null == o && h(), o
                }, reliableMarginRight: function () {
                    var f, i = t.appendChild(u.createElement("div"));
                    return i.style.cssText = t.style.cssText = c, i.style.marginRight = i.style.width = "0", t.style.width = "1px", e.appendChild(r), f = !parseFloat(n.getComputedStyle(i, null).marginRight), e.removeChild(r), t.innerHTML = "", f
                }
            })
        }();
        i.swap = function (n, t, i, r) {
            var f, u, e = {};
            for (u in t)e[u] = n.style[u], n.style[u] = t[u];
            f = i.apply(n, r || []);
            for (u in t)n.style[u] = e[u];
            return f
        };
        var gf = /^(none|table(?!-c[ea]).+)/, ne = new RegExp("^(" + lt + ")(.*)$", "i"), te = new RegExp("^([+-])=(" + lt + ")", "i"), ie = {
            position: "absolute",
            visibility: "hidden",
            display: "block"
        }, kr = {letterSpacing: 0, fontWeight: 400}, dr = ["Webkit", "O", "Moz", "ms"];
        i.extend({
            cssHooks: {
                opacity: {
                    get: function (n, t) {
                        if (t) {
                            var i = rt(n, "opacity");
                            return "" === i ? "1" : i
                        }
                    }
                }
            },
            cssNumber: {
                columnCount: !0,
                fillOpacity: !0,
                fontWeight: !0,
                lineHeight: !0,
                opacity: !0,
                order: !0,
                orphans: !0,
                widows: !0,
                zIndex: !0,
                zoom: !0
            },
            cssProps: {float: "cssFloat"},
            style: function (n, t, r, u) {
                if (n && 3 !== n.nodeType && 8 !== n.nodeType && n.style) {
                    var o, c, e, s = i.camelCase(t), h = n.style;
                    return t = i.cssProps[s] || (i.cssProps[s] = gr(h, s)), e = i.cssHooks[t] || i.cssHooks[s], void 0 === r ? e && "get"in e && void 0 !== (o = e.get(n, !1, u)) ? o : h[t] : (c = typeof r, "string" === c && (o = te.exec(r)) && (r = (o[1] + 1) * o[2] + parseFloat(i.css(n, t)), c = "number"), null != r && r === r && ("number" !== c || i.cssNumber[s] || (r += "px"), f.clearCloneStyle || "" !== r || 0 !== t.indexOf("background") || (h[t] = "inherit"), e && "set"in e && void 0 === (r = e.set(n, r, u)) || (h[t] = "", h[t] = r)), void 0)
                }
            },
            css: function (n, t, r, u) {
                var f, s, e, o = i.camelCase(t);
                return t = i.cssProps[o] || (i.cssProps[o] = gr(n.style, o)), e = i.cssHooks[t] || i.cssHooks[o], e && "get"in e && (f = e.get(n, !0, r)), void 0 === f && (f = rt(n, t, u)), "normal" === f && t in kr && (f = kr[t]), "" === r || r ? (s = parseFloat(f), r === !0 || i.isNumeric(s) ? s || 0 : f) : f
            }
        });
        i.each(["height", "width"], function (n, t) {
            i.cssHooks[t] = {
                get: function (n, r, u) {
                    if (r)return 0 === n.offsetWidth && gf.test(i.css(n, "display")) ? i.swap(n, ie, function () {
                        return iu(n, t, u)
                    }) : iu(n, t, u)
                }, set: function (n, r, u) {
                    var f = u && yt(n);
                    return nu(n, r, u ? tu(n, t, u, "border-box" === i.css(n, "boxSizing", !1, f), f) : 0)
                }
            }
        });
        i.cssHooks.marginRight = br(f.reliableMarginRight, function (n, t) {
            if (t)return i.swap(n, {display: "inline-block"}, rt, [n, "marginRight"])
        });
        i.each({margin: "", padding: "", border: "Width"}, function (n, t) {
            i.cssHooks[n + t] = {
                expand: function (i) {
                    for (var r = 0, f = {}, u = "string" == typeof i ? i.split(" ") : [i]; 4 > r; r++)f[n + w[r] + t] = u[r] || u[r - 2] || u[0];
                    return f
                }
            };
            wr.test(n) || (i.cssHooks[n + t].set = nu)
        });
        i.fn.extend({
            css: function (n, t) {
                return l(this, function (n, t, r) {
                    var f, e, o = {}, u = 0;
                    if (i.isArray(t)) {
                        for (f = yt(n), e = t.length; e > u; u++)o[t[u]] = i.css(n, t[u], !1, f);
                        return o
                    }
                    return void 0 !== r ? i.style(n, t, r) : i.css(n, t)
                }, n, t, arguments.length > 1)
            }, show: function () {
                return ru(this, !0)
            }, hide: function () {
                return ru(this)
            }, toggle: function (n) {
                return "boolean" == typeof n ? n ? this.show() : this.hide() : this.each(function () {
                    it(this) ? i(this).show() : i(this).hide()
                })
            }
        });
        i.Tween = s;
        s.prototype = {
            constructor: s, init: function (n, t, r, u, f, e) {
                this.elem = n;
                this.prop = r;
                this.easing = f || "swing";
                this.options = t;
                this.start = this.now = this.cur();
                this.end = u;
                this.unit = e || (i.cssNumber[r] ? "" : "px")
            }, cur: function () {
                var n = s.propHooks[this.prop];
                return n && n.get ? n.get(this) : s.propHooks._default.get(this)
            }, run: function (n) {
                var r, t = s.propHooks[this.prop];
                return this.pos = r = this.options.duration ? i.easing[this.easing](n, this.options.duration * n, 0, 1, this.options.duration) : n, this.now = (this.end - this.start) * r + this.start, this.options.step && this.options.step.call(this.elem, this.now, this), t && t.set ? t.set(this) : s.propHooks._default.set(this), this
            }
        };
        s.prototype.init.prototype = s.prototype;
        s.propHooks = {
            _default: {
                get: function (n) {
                    var t;
                    return null == n.elem[n.prop] || n.elem.style && null != n.elem.style[n.prop] ? (t = i.css(n.elem, n.prop, ""), t && "auto" !== t ? t : 0) : n.elem[n.prop]
                }, set: function (n) {
                    i.fx.step[n.prop] ? i.fx.step[n.prop](n) : n.elem.style && (null != n.elem.style[i.cssProps[n.prop]] || i.cssHooks[n.prop]) ? i.style(n.elem, n.prop, n.now + n.unit) : n.elem[n.prop] = n.now
                }
            }
        };
        s.propHooks.scrollTop = s.propHooks.scrollLeft = {
            set: function (n) {
                n.elem.nodeType && n.elem.parentNode && (n.elem[n.prop] = n.now)
            }
        };
        i.easing = {
            linear: function (n) {
                return n
            }, swing: function (n) {
                return .5 - Math.cos(n * Math.PI) / 2
            }
        };
        i.fx = s.prototype.init;
        i.fx.step = {};
        var nt, pt, re = /^(?:toggle|show|hide)$/, uu = new RegExp("^(?:([+-])=|)(" + lt + ")([a-z%]*)$", "i"), ue = /queueHooks$/, wt = [fe], ut = {
            "*": [function (n, t) {
                var f = this.createTween(n, t), s = f.cur(), r = uu.exec(t), e = r && r[3] || (i.cssNumber[n] ? "" : "px"), u = (i.cssNumber[n] || "px" !== e && +s) && uu.exec(i.css(f.elem, n)), o = 1, h = 20;
                if (u && u[3] !== e) {
                    e = e || u[3];
                    r = r || [];
                    u = +s || 1;
                    do o = o || ".5", u /= o, i.style(f.elem, n, u + e); while (o !== (o = f.cur() / s) && 1 !== o && --h)
                }
                return r && (u = f.start = +u || +s || 0, f.unit = e, f.end = r[1] ? u + (r[1] + 1) * r[2] : +r[2]), f
            }]
        };
        i.Animation = i.extend(ou, {
            tweener: function (n, t) {
                i.isFunction(n) ? (t = n, n = ["*"]) : n = n.split(" ");
                for (var r, u = 0, f = n.length; f > u; u++)r = n[u], ut[r] = ut[r] || [], ut[r].unshift(t)
            }, prefilter: function (n, t) {
                t ? wt.unshift(n) : wt.push(n)
            }
        });
        i.speed = function (n, t, r) {
            var u = n && "object" == typeof n ? i.extend({}, n) : {
                complete: r || !r && t || i.isFunction(n) && n,
                duration: n,
                easing: r && t || t && !i.isFunction(t) && t
            };
            return u.duration = i.fx.off ? 0 : "number" == typeof u.duration ? u.duration : u.duration in i.fx.speeds ? i.fx.speeds[u.duration] : i.fx.speeds._default, (null == u.queue || u.queue === !0) && (u.queue = "fx"), u.old = u.complete, u.complete = function () {
                i.isFunction(u.old) && u.old.call(this);
                u.queue && i.dequeue(this, u.queue)
            }, u
        };
        i.fn.extend({
            fadeTo: function (n, t, i, r) {
                return this.filter(it).css("opacity", 0).show().end().animate({opacity: t}, n, i, r)
            }, animate: function (n, t, u, f) {
                var s = i.isEmptyObject(n), o = i.speed(t, u, f), e = function () {
                    var t = ou(this, i.extend({}, n), o);
                    (s || r.get(this, "finish")) && t.stop(!0)
                };
                return e.finish = e, s || o.queue === !1 ? this.each(e) : this.queue(o.queue, e)
            }, stop: function (n, t, u) {
                var f = function (n) {
                    var t = n.stop;
                    delete n.stop;
                    t(u)
                };
                return "string" != typeof n && (u = t, t = n, n = void 0), t && n !== !1 && this.queue(n || "fx", []), this.each(function () {
                    var s = !0, t = null != n && n + "queueHooks", o = i.timers, e = r.get(this);
                    if (t)e[t] && e[t].stop && f(e[t]); else for (t in e)e[t] && e[t].stop && ue.test(t) && f(e[t]);
                    for (t = o.length; t--;)o[t].elem !== this || null != n && o[t].queue !== n || (o[t].anim.stop(u), s = !1, o.splice(t, 1));
                    (s || !u) && i.dequeue(this, n)
                })
            }, finish: function (n) {
                return n !== !1 && (n = n || "fx"), this.each(function () {
                    var t, e = r.get(this), u = e[n + "queue"], o = e[n + "queueHooks"], f = i.timers, s = u ? u.length : 0;
                    for (e.finish = !0, i.queue(this, n, []), o && o.stop && o.stop.call(this, !0), t = f.length; t--;)f[t].elem === this && f[t].queue === n && (f[t].anim.stop(!0), f.splice(t, 1));
                    for (t = 0; s > t; t++)u[t] && u[t].finish && u[t].finish.call(this);
                    delete e.finish
                })
            }
        });
        i.each(["toggle", "show", "hide"], function (n, t) {
            var r = i.fn[t];
            i.fn[t] = function (n, i, u) {
                return null == n || "boolean" == typeof n ? r.apply(this, arguments) : this.animate(bt(t, !0), n, i, u)
            }
        });
        i.each({
            slideDown: bt("show"),
            slideUp: bt("hide"),
            slideToggle: bt("toggle"),
            fadeIn: {opacity: "show"},
            fadeOut: {opacity: "hide"},
            fadeToggle: {opacity: "toggle"}
        }, function (n, t) {
            i.fn[n] = function (n, i, r) {
                return this.animate(t, n, i, r)
            }
        });
        i.timers = [];
        i.fx.tick = function () {
            var r, n = 0, t = i.timers;
            for (nt = i.now(); n < t.length; n++)r = t[n], r() || t[n] !== r || t.splice(n--, 1);
            t.length || i.fx.stop();
            nt = void 0
        };
        i.fx.timer = function (n) {
            i.timers.push(n);
            n() ? i.fx.start() : i.timers.pop()
        };
        i.fx.interval = 13;
        i.fx.start = function () {
            pt || (pt = setInterval(i.fx.tick, i.fx.interval))
        };
        i.fx.stop = function () {
            clearInterval(pt);
            pt = null
        };
        i.fx.speeds = {slow: 600, fast: 200, _default: 400};
        i.fn.delay = function (n, t) {
            return n = i.fx ? i.fx.speeds[n] || n : n, t = t || "fx", this.queue(t, function (t, i) {
                var r = setTimeout(t, n);
                i.stop = function () {
                    clearTimeout(r)
                }
            })
        }, function () {
            var n = u.createElement("input"), t = u.createElement("select"), i = t.appendChild(u.createElement("option"));
            n.type = "checkbox";
            f.checkOn = "" !== n.value;
            f.optSelected = i.selected;
            t.disabled = !0;
            f.optDisabled = !i.disabled;
            n = u.createElement("input");
            n.value = "t";
            n.type = "radio";
            f.radioValue = "t" === n.value
        }();
        tt = i.expr.attrHandle;
        i.fn.extend({
            attr: function (n, t) {
                return l(this, i.attr, n, t, arguments.length > 1)
            }, removeAttr: function (n) {
                return this.each(function () {
                    i.removeAttr(this, n)
                })
            }
        });
        i.extend({
            attr: function (n, t, r) {
                var u, f, e = n.nodeType;
                if (n && 3 !== e && 8 !== e && 2 !== e)return typeof n.getAttribute === d ? i.prop(n, t, r) : (1 === e && i.isXMLDoc(n) || (t = t.toLowerCase(), u = i.attrHooks[t] || (i.expr.match.bool.test(t) ? su : oe)), void 0 === r ? u && "get"in u && null !== (f = u.get(n, t)) ? f : (f = i.find.attr(n, t), null == f ? void 0 : f) : null !== r ? u && "set"in u && void 0 !== (f = u.set(n, r, t)) ? f : (n.setAttribute(t, r + ""), r) : void i.removeAttr(n, t))
            }, removeAttr: function (n, t) {
                var r, u, e = 0, f = t && t.match(c);
                if (f && 1 === n.nodeType)while (r = f[e++])u = i.propFix[r] || r, i.expr.match.bool.test(r) && (n[u] = !1), n.removeAttribute(r)
            }, attrHooks: {
                type: {
                    set: function (n, t) {
                        if (!f.radioValue && "radio" === t && i.nodeName(n, "input")) {
                            var r = n.value;
                            return n.setAttribute("type", t), r && (n.value = r), t
                        }
                    }
                }
            }
        });
        su = {
            set: function (n, t, r) {
                return t === !1 ? i.removeAttr(n, r) : n.setAttribute(r, r), r
            }
        };
        i.each(i.expr.match.bool.source.match(/\w+/g), function (n, t) {
            var r = tt[t] || i.find.attr;
            tt[t] = function (n, t, i) {
                var u, f;
                return i || (f = tt[t], tt[t] = u, u = null != r(n, t, i) ? t.toLowerCase() : null, tt[t] = f), u
            }
        });
        hu = /^(?:input|select|textarea|button)$/i;
        i.fn.extend({
            prop: function (n, t) {
                return l(this, i.prop, n, t, arguments.length > 1)
            }, removeProp: function (n) {
                return this.each(function () {
                    delete this[i.propFix[n] || n]
                })
            }
        });
        i.extend({
            propFix: {"for": "htmlFor", "class": "className"}, prop: function (n, t, r) {
                var f, u, o, e = n.nodeType;
                if (n && 3 !== e && 8 !== e && 2 !== e)return o = 1 !== e || !i.isXMLDoc(n), o && (t = i.propFix[t] || t, u = i.propHooks[t]), void 0 !== r ? u && "set"in u && void 0 !== (f = u.set(n, r, t)) ? f : n[t] = r : u && "get"in u && null !== (f = u.get(n, t)) ? f : n[t]
            }, propHooks: {
                tabIndex: {
                    get: function (n) {
                        return n.hasAttribute("tabindex") || hu.test(n.nodeName) || n.href ? n.tabIndex : -1
                    }
                }
            }
        });
        f.optSelected || (i.propHooks.selected = {
            get: function (n) {
                var t = n.parentNode;
                return t && t.parentNode && t.parentNode.selectedIndex, null
            }
        });
        i.each(["tabIndex", "readOnly", "maxLength", "cellSpacing", "cellPadding", "rowSpan", "colSpan", "useMap", "frameBorder", "contentEditable"], function () {
            i.propFix[this.toLowerCase()] = this
        });
        kt = /[\t\r\n\f]/g;
        i.fn.extend({
            addClass: function (n) {
                var o, t, r, u, s, f, h = "string" == typeof n && n, e = 0, l = this.length;
                if (i.isFunction(n))return this.each(function (t) {
                    i(this).addClass(n.call(this, t, this.className))
                });
                if (h)for (o = (n || "").match(c) || []; l > e; e++)if (t = this[e], r = 1 === t.nodeType && (t.className ? (" " + t.className + " ").replace(kt, " ") : " ")) {
                    for (s = 0; u = o[s++];)r.indexOf(" " + u + " ") < 0 && (r += u + " ");
                    f = i.trim(r);
                    t.className !== f && (t.className = f)
                }
                return this
            }, removeClass: function (n) {
                var o, t, r, u, s, f, h = 0 === arguments.length || "string" == typeof n && n, e = 0, l = this.length;
                if (i.isFunction(n))return this.each(function (t) {
                    i(this).removeClass(n.call(this, t, this.className))
                });
                if (h)for (o = (n || "").match(c) || []; l > e; e++)if (t = this[e], r = 1 === t.nodeType && (t.className ? (" " + t.className + " ").replace(kt, " ") : "")) {
                    for (s = 0; u = o[s++];)while (r.indexOf(" " + u + " ") >= 0)r = r.replace(" " + u + " ", " ");
                    f = n ? i.trim(r) : "";
                    t.className !== f && (t.className = f)
                }
                return this
            }, toggleClass: function (n, t) {
                var u = typeof n;
                return "boolean" == typeof t && "string" === u ? t ? this.addClass(n) : this.removeClass(n) : this.each(i.isFunction(n) ? function (r) {
                    i(this).toggleClass(n.call(this, r, this.className, t), t)
                } : function () {
                    if ("string" === u)for (var t, e = 0, f = i(this), o = n.match(c) || []; t = o[e++];)f.hasClass(t) ? f.removeClass(t) : f.addClass(t); else(u === d || "boolean" === u) && (this.className && r.set(this, "__className__", this.className), this.className = this.className || n === !1 ? "" : r.get(this, "__className__") || "")
                })
            }, hasClass: function (n) {
                for (var i = " " + n + " ", t = 0, r = this.length; r > t; t++)if (1 === this[t].nodeType && (" " + this[t].className + " ").replace(kt, " ").indexOf(i) >= 0)return !0;
                return !1
            }
        });
        cu = /\r/g;
        i.fn.extend({
            val: function (n) {
                var t, r, f, u = this[0];
                return arguments.length ? (f = i.isFunction(n), this.each(function (r) {
                    var u;
                    1 === this.nodeType && (u = f ? n.call(this, r, i(this).val()) : n, null == u ? u = "" : "number" == typeof u ? u += "" : i.isArray(u) && (u = i.map(u, function (n) {
                        return null == n ? "" : n + ""
                    })), t = i.valHooks[this.type] || i.valHooks[this.nodeName.toLowerCase()], t && "set"in t && void 0 !== t.set(this, u, "value") || (this.value = u))
                })) : u ? (t = i.valHooks[u.type] || i.valHooks[u.nodeName.toLowerCase()], t && "get"in t && void 0 !== (r = t.get(u, "value")) ? r : (r = u.value, "string" == typeof r ? r.replace(cu, "") : null == r ? "" : r)) : void 0
            }
        });
        i.extend({
            valHooks: {
                select: {
                    get: function (n) {
                        for (var o, t, s = n.options, r = n.selectedIndex, u = "select-one" === n.type || 0 > r, h = u ? null : [], c = u ? r + 1 : s.length, e = 0 > r ? c : u ? r : 0; c > e; e++)if (t = s[e], !(!t.selected && e !== r || (f.optDisabled ? t.disabled : null !== t.getAttribute("disabled")) || t.parentNode.disabled && i.nodeName(t.parentNode, "optgroup"))) {
                            if (o = i(t).val(), u)return o;
                            h.push(o)
                        }
                        return h
                    }, set: function (n, t) {
                        for (var u, r, f = n.options, e = i.makeArray(t), o = f.length; o--;)r = f[o], (r.selected = i.inArray(i(r).val(), e) >= 0) && (u = !0);
                        return u || (n.selectedIndex = -1), e
                    }
                }
            }
        });
        i.each(["radio", "checkbox"], function () {
            i.valHooks[this] = {
                set: function (n, t) {
                    if (i.isArray(t))return n.checked = i.inArray(i(n).val(), t) >= 0
                }
            };
            f.checkOn || (i.valHooks[this].get = function (n) {
                return null === n.getAttribute("value") ? "on" : n.value
            })
        });
        i.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "), function (n, t) {
            i.fn[t] = function (n, i) {
                return arguments.length > 0 ? this.on(t, null, n, i) : this.trigger(t)
            }
        });
        i.fn.extend({
            hover: function (n, t) {
                return this.mouseenter(n).mouseleave(t || n)
            }, bind: function (n, t, i) {
                return this.on(n, null, t, i)
            }, unbind: function (n, t) {
                return this.off(n, null, t)
            }, delegate: function (n, t, i, r) {
                return this.on(t, n, i, r)
            }, undelegate: function (n, t, i) {
                return 1 === arguments.length ? this.off(n, "**") : this.off(t, n || "**", i)
            }
        });
        dt = i.now();
        gt = /\?/;
        i.parseJSON = function (n) {
            return JSON.parse(n + "")
        };
        i.parseXML = function (n) {
            var t, r;
            if (!n || "string" != typeof n)return null;
            try {
                r = new DOMParser;
                t = r.parseFromString(n, "text/xml")
            } catch (u) {
                t = void 0
            }
            return (!t || t.getElementsByTagName("parsererror").length) && i.error("Invalid XML: " + n), t
        };
        var b, v, se = /#.*$/, lu = /([?&])_=[^&]*/, he = /^(.*?):[ \t]*([^\r\n]*)$/gm, ce = /^(?:GET|HEAD)$/, le = /^\/\//, au = /^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/, vu = {}, ci = {}, yu = "*/".concat("*");
        try {
            v = location.href
        } catch (ge) {
            v = u.createElement("a");
            v.href = "";
            v = v.href
        }
        b = au.exec(v.toLowerCase()) || [];
        i.extend({
            active: 0,
            lastModified: {},
            etag: {},
            ajaxSettings: {
                url: v,
                type: "GET",
                isLocal: /^(?:about|app|app-storage|.+-extension|file|res|widget):$/.test(b[1]),
                global: !0,
                processData: !0,
                async: !0,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                accepts: {
                    "*": yu,
                    text: "text/plain",
                    html: "text/html",
                    xml: "application/xml, text/xml",
                    json: "application/json, text/javascript"
                },
                contents: {xml: /xml/, html: /html/, json: /json/},
                responseFields: {xml: "responseXML", text: "responseText", json: "responseJSON"},
                converters: {"* text": String, "text html": !0, "text json": i.parseJSON, "text xml": i.parseXML},
                flatOptions: {url: !0, context: !0}
            },
            ajaxSetup: function (n, t) {
                return t ? li(li(n, i.ajaxSettings), t) : li(i.ajaxSettings, n)
            },
            ajaxPrefilter: pu(vu),
            ajaxTransport: pu(ci),
            ajax: function (n, t) {
                function w(n, t, s, h) {
                    var v, it, b, y, w, c = t;
                    2 !== e && (e = 2, d && clearTimeout(d), l = void 0, k = h || "", u.readyState = n > 0 ? 4 : 0, v = n >= 200 && 300 > n || 304 === n, s && (y = ae(r, u, s)), y = ve(r, y, u, v), v ? (r.ifModified && (w = u.getResponseHeader("Last-Modified"), w && (i.lastModified[f] = w), w = u.getResponseHeader("etag"), w && (i.etag[f] = w)), 204 === n || "HEAD" === r.type ? c = "nocontent" : 304 === n ? c = "notmodified" : (c = y.state, it = y.data, b = y.error, v = !b)) : (b = c, (n || !c) && (c = "error", 0 > n && (n = 0))), u.status = n, u.statusText = (t || c) + "", v ? nt.resolveWith(o, [it, c, u]) : nt.rejectWith(o, [u, c, b]), u.statusCode(p), p = void 0, a && g.trigger(v ? "ajaxSuccess" : "ajaxError", [u, r, v ? it : b]), tt.fireWith(o, [u, c]), a && (g.trigger("ajaxComplete", [u, r]), --i.active || i.event.trigger("ajaxStop")))
                }

                "object" == typeof n && (t = n, n = void 0);
                t = t || {};
                var l, f, k, y, d, s, a, h, r = i.ajaxSetup({}, t), o = r.context || r, g = r.context && (o.nodeType || o.jquery) ? i(o) : i.event, nt = i.Deferred(), tt = i.Callbacks("once memory"), p = r.statusCode || {}, it = {}, rt = {}, e = 0, ut = "canceled", u = {
                    readyState: 0,
                    getResponseHeader: function (n) {
                        var t;
                        if (2 === e) {
                            if (!y)for (y = {}; t = he.exec(k);)y[t[1].toLowerCase()] = t[2];
                            t = y[n.toLowerCase()]
                        }
                        return null == t ? null : t
                    },
                    getAllResponseHeaders: function () {
                        return 2 === e ? k : null
                    },
                    setRequestHeader: function (n, t) {
                        var i = n.toLowerCase();
                        return e || (n = rt[i] = rt[i] || n, it[n] = t), this
                    },
                    overrideMimeType: function (n) {
                        return e || (r.mimeType = n), this
                    },
                    statusCode: function (n) {
                        var t;
                        if (n)if (2 > e)for (t in n)p[t] = [p[t], n[t]]; else u.always(n[u.status]);
                        return this
                    },
                    abort: function (n) {
                        var t = n || ut;
                        return l && l.abort(t), w(0, t), this
                    }
                };
                if (nt.promise(u).complete = tt.add, u.success = u.done, u.error = u.fail, r.url = ((n || r.url || v) + "").replace(se, "").replace(le, b[1] + "//"), r.type = t.method || t.type || r.method || r.type, r.dataTypes = i.trim(r.dataType || "*").toLowerCase().match(c) || [""], null == r.crossDomain && (s = au.exec(r.url.toLowerCase()), r.crossDomain = !(!s || s[1] === b[1] && s[2] === b[2] && (s[3] || ("http:" === s[1] ? "80" : "443")) === (b[3] || ("http:" === b[1] ? "80" : "443")))), r.data && r.processData && "string" != typeof r.data && (r.data = i.param(r.data, r.traditional)), wu(vu, r, t, u), 2 === e)return u;
                a = r.global;
                a && 0 == i.active++ && i.event.trigger("ajaxStart");
                r.type = r.type.toUpperCase();
                r.hasContent = !ce.test(r.type);
                f = r.url;
                r.hasContent || (r.data && (f = r.url += (gt.test(f) ? "&" : "?") + r.data, delete r.data), r.cache === !1 && (r.url = lu.test(f) ? f.replace(lu, "$1_=" + dt++) : f + (gt.test(f) ? "&" : "?") + "_=" + dt++));
                r.ifModified && (i.lastModified[f] && u.setRequestHeader("If-Modified-Since", i.lastModified[f]), i.etag[f] && u.setRequestHeader("If-None-Match", i.etag[f]));
                (r.data && r.hasContent && r.contentType !== !1 || t.contentType) && u.setRequestHeader("Content-Type", r.contentType);
                u.setRequestHeader("Accept", r.dataTypes[0] && r.accepts[r.dataTypes[0]] ? r.accepts[r.dataTypes[0]] + ("*" !== r.dataTypes[0] ? ", " + yu + "; q=0.01" : "") : r.accepts["*"]);
                for (h in r.headers)u.setRequestHeader(h, r.headers[h]);
                if (r.beforeSend && (r.beforeSend.call(o, u, r) === !1 || 2 === e))return u.abort();
                ut = "abort";
                for (h in{success: 1, error: 1, complete: 1})u[h](r[h]);
                if (l = wu(ci, r, t, u)) {
                    u.readyState = 1;
                    a && g.trigger("ajaxSend", [u, r]);
                    r.async && r.timeout > 0 && (d = setTimeout(function () {
                        u.abort("timeout")
                    }, r.timeout));
                    try {
                        e = 1;
                        l.send(it, w)
                    } catch (ft) {
                        if (!(2 > e))throw ft;
                        w(-1, ft)
                    }
                } else w(-1, "No Transport");
                return u
            },
            getJSON: function (n, t, r) {
                return i.get(n, t, r, "json")
            },
            getScript: function (n, t) {
                return i.get(n, void 0, t, "script")
            }
        });
        i.each(["get", "post"], function (n, t) {
            i[t] = function (n, r, u, f) {
                return i.isFunction(r) && (f = f || u, u = r, r = void 0), i.ajax({
                    url: n,
                    type: t,
                    dataType: f,
                    data: r,
                    success: u
                })
            }
        });
        i.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"], function (n, t) {
            i.fn[t] = function (n) {
                return this.on(t, n)
            }
        });
        i._evalUrl = function (n) {
            return i.ajax({url: n, type: "GET", dataType: "script", async: !1, global: !1, throws: !0})
        };
        i.fn.extend({
            wrapAll: function (n) {
                var t;
                return i.isFunction(n) ? this.each(function (t) {
                    i(this).wrapAll(n.call(this, t))
                }) : (this[0] && (t = i(n, this[0].ownerDocument).eq(0).clone(!0), this[0].parentNode && t.insertBefore(this[0]), t.map(function () {
                    for (var n = this; n.firstElementChild;)n = n.firstElementChild;
                    return n
                }).append(this)), this)
            }, wrapInner: function (n) {
                return this.each(i.isFunction(n) ? function (t) {
                    i(this).wrapInner(n.call(this, t))
                } : function () {
                    var t = i(this), r = t.contents();
                    r.length ? r.wrapAll(n) : t.append(n)
                })
            }, wrap: function (n) {
                var t = i.isFunction(n);
                return this.each(function (r) {
                    i(this).wrapAll(t ? n.call(this, r) : n)
                })
            }, unwrap: function () {
                return this.parent().each(function () {
                    i.nodeName(this, "body") || i(this).replaceWith(this.childNodes)
                }).end()
            }
        });
        i.expr.filters.hidden = function (n) {
            return n.offsetWidth <= 0 && n.offsetHeight <= 0
        };
        i.expr.filters.visible = function (n) {
            return !i.expr.filters.hidden(n)
        };
        var ye = /%20/g, pe = /\[\]$/, bu = /\r?\n/g, we = /^(?:submit|button|image|reset|file)$/i, be = /^(?:input|select|textarea|keygen)/i;
        i.param = function (n, t) {
            var r, u = [], f = function (n, t) {
                t = i.isFunction(t) ? t() : null == t ? "" : t;
                u[u.length] = encodeURIComponent(n) + "=" + encodeURIComponent(t)
            };
            if (void 0 === t && (t = i.ajaxSettings && i.ajaxSettings.traditional), i.isArray(n) || n.jquery && !i.isPlainObject(n))i.each(n, function () {
                f(this.name, this.value)
            }); else for (r in n)ai(r, n[r], t, f);
            return u.join("&").replace(ye, "+")
        };
        i.fn.extend({
            serialize: function () {
                return i.param(this.serializeArray())
            }, serializeArray: function () {
                return this.map(function () {
                    var n = i.prop(this, "elements");
                    return n ? i.makeArray(n) : this
                }).filter(function () {
                    var n = this.type;
                    return this.name && !i(this).is(":disabled") && be.test(this.nodeName) && !we.test(n) && (this.checked || !fr.test(n))
                }).map(function (n, t) {
                    var r = i(this).val();
                    return null == r ? null : i.isArray(r) ? i.map(r, function (n) {
                        return {name: t.name, value: n.replace(bu, "\r\n")}
                    }) : {name: t.name, value: r.replace(bu, "\r\n")}
                }).get()
            }
        });
        i.ajaxSettings.xhr = function () {
            try {
                return new XMLHttpRequest
            } catch (n) {
            }
        };
        var ke = 0, ni = {}, de = {0: 200, 1223: 204}, ft = i.ajaxSettings.xhr();
        return n.ActiveXObject && i(n).on("unload", function () {
            for (var n in ni)ni[n]()
        }), f.cors = !!ft && "withCredentials"in ft, f.ajax = ft = !!ft, i.ajaxTransport(function (n) {
            var t;
            if (f.cors || ft && !n.crossDomain)return {
                send: function (i, r) {
                    var f, u = n.xhr(), e = ++ke;
                    if (u.open(n.type, n.url, n.async, n.username, n.password), n.xhrFields)for (f in n.xhrFields)u[f] = n.xhrFields[f];
                    n.mimeType && u.overrideMimeType && u.overrideMimeType(n.mimeType);
                    n.crossDomain || i["X-Requested-With"] || (i["X-Requested-With"] = "XMLHttpRequest");
                    for (f in i)u.setRequestHeader(f, i[f]);
                    t = function (n) {
                        return function () {
                            t && (delete ni[e], t = u.onload = u.onerror = null, "abort" === n ? u.abort() : "error" === n ? r(u.status, u.statusText) : r(de[u.status] || u.status, u.statusText, "string" == typeof u.responseText ? {text: u.responseText} : void 0, u.getAllResponseHeaders()))
                        }
                    };
                    u.onload = t();
                    u.onerror = t("error");
                    t = ni[e] = t("abort");
                    u.send(n.hasContent && n.data || null)
                }, abort: function () {
                    t && t()
                }
            }
        }), i.ajaxSetup({
            accepts: {script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"},
            contents: {script: /(?:java|ecma)script/},
            converters: {
                "text script": function (n) {
                    return i.globalEval(n), n
                }
            }
        }), i.ajaxPrefilter("script", function (n) {
            void 0 === n.cache && (n.cache = !1);
            n.crossDomain && (n.type = "GET")
        }), i.ajaxTransport("script", function (n) {
            if (n.crossDomain) {
                var r, t;
                return {
                    send: function (f, e) {
                        r = i("<script>").prop({
                            async: !0,
                            charset: n.scriptCharset,
                            src: n.url
                        }).on("load error", t = function (n) {
                            r.remove();
                            t = null;
                            n && e("error" === n.type ? 404 : 200, n.type)
                        });
                        u.head.appendChild(r[0])
                    }, abort: function () {
                        t && t()
                    }
                }
            }
        }), vi = [], ti = /(=)\?(?=&|$)|\?\?/, i.ajaxSetup({
            jsonp: "callback", jsonpCallback: function () {
                var n = vi.pop() || i.expando + "_" + dt++;
                return this[n] = !0, n
            }
        }), i.ajaxPrefilter("json jsonp", function (t, r, u) {
            var f, o, e, s = t.jsonp !== !1 && (ti.test(t.url) ? "url" : "string" == typeof t.data && !(t.contentType || "").indexOf("application/x-www-form-urlencoded") && ti.test(t.data) && "data");
            if (s || "jsonp" === t.dataTypes[0])return (f = t.jsonpCallback = i.isFunction(t.jsonpCallback) ? t.jsonpCallback() : t.jsonpCallback, s ? t[s] = t[s].replace(ti, "$1" + f) : t.jsonp !== !1 && (t.url += (gt.test(t.url) ? "&" : "?") + t.jsonp + "=" + f), t.converters["script json"] = function () {
                return e || i.error(f + " was not called"), e[0]
            }, t.dataTypes[0] = "json", o = n[f], n[f] = function () {
                e = arguments
            }, u.always(function () {
                n[f] = o;
                t[f] && (t.jsonpCallback = r.jsonpCallback, vi.push(f));
                e && i.isFunction(o) && o(e[0]);
                e = o = void 0
            }), "script")
        }), i.parseHTML = function (n, t, r) {
            if (!n || "string" != typeof n)return null;
            "boolean" == typeof t && (r = t, t = !1);
            t = t || u;
            var f = di.exec(n), e = !r && [];
            return f ? [t.createElement(f[1])] : (f = i.buildFragment([n], t, e), e && e.length && i(e).remove(), i.merge([], f.childNodes))
        }, yi = i.fn.load, i.fn.load = function (n, t, r) {
            if ("string" != typeof n && yi)return yi.apply(this, arguments);
            var u, o, s, f = this, e = n.indexOf(" ");
            return e >= 0 && (u = n.slice(e), n = n.slice(0, e)), i.isFunction(t) ? (r = t, t = void 0) : t && "object" == typeof t && (o = "POST"), f.length > 0 && i.ajax({
                url: n,
                type: o,
                dataType: "html",
                data: t
            }).done(function (n) {
                s = arguments;
                f.html(u ? i("<div>").append(i.parseHTML(n)).find(u) : n)
            }).complete(r && function (n, t) {
                    f.each(r, s || [n.responseText, t, n])
                }), this
        }, i.expr.filters.animated = function (n) {
            return i.grep(i.timers, function (t) {
                return n === t.elem
            }).length
        }, pi = n.document.documentElement, i.offset = {
            setOffset: function (n, t, r) {
                var e, o, s, h, u, c, v, l = i.css(n, "position"), a = i(n), f = {};
                "static" === l && (n.style.position = "relative");
                u = a.offset();
                s = i.css(n, "top");
                c = i.css(n, "left");
                v = ("absolute" === l || "fixed" === l) && (s + c).indexOf("auto") > -1;
                v ? (e = a.position(), h = e.top, o = e.left) : (h = parseFloat(s) || 0, o = parseFloat(c) || 0);
                i.isFunction(t) && (t = t.call(n, r, u));
                null != t.top && (f.top = t.top - u.top + h);
                null != t.left && (f.left = t.left - u.left + o);
                "using"in t ? t.using.call(n, f) : a.css(f)
            }
        }, i.fn.extend({
            offset: function (n) {
                if (arguments.length)return void 0 === n ? this : this.each(function (t) {
                    i.offset.setOffset(this, n, t)
                });
                var r, f, t = this[0], u = {top: 0, left: 0}, e = t && t.ownerDocument;
                if (e)return r = e.documentElement, i.contains(r, t) ? (typeof t.getBoundingClientRect !== d && (u = t.getBoundingClientRect()), f = ku(e), {
                    top: u.top + f.pageYOffset - r.clientTop,
                    left: u.left + f.pageXOffset - r.clientLeft
                }) : u
            }, position: function () {
                if (this[0]) {
                    var n, r, u = this[0], t = {top: 0, left: 0};
                    return "fixed" === i.css(u, "position") ? r = u.getBoundingClientRect() : (n = this.offsetParent(), r = this.offset(), i.nodeName(n[0], "html") || (t = n.offset()), t.top += i.css(n[0], "borderTopWidth", !0), t.left += i.css(n[0], "borderLeftWidth", !0)), {
                        top: r.top - t.top - i.css(u, "marginTop", !0),
                        left: r.left - t.left - i.css(u, "marginLeft", !0)
                    }
                }
            }, offsetParent: function () {
                return this.map(function () {
                    for (var n = this.offsetParent || pi; n && !i.nodeName(n, "html") && "static" === i.css(n, "position");)n = n.offsetParent;
                    return n || pi
                })
            }
        }), i.each({scrollLeft: "pageXOffset", scrollTop: "pageYOffset"}, function (t, r) {
            var u = "pageYOffset" === r;
            i.fn[t] = function (i) {
                return l(this, function (t, i, f) {
                    var e = ku(t);
                    return void 0 === f ? e ? e[r] : t[i] : void(e ? e.scrollTo(u ? n.pageXOffset : f, u ? f : n.pageYOffset) : t[i] = f)
                }, t, i, arguments.length, null)
            }
        }), i.each(["top", "left"], function (n, t) {
            i.cssHooks[t] = br(f.pixelPosition, function (n, r) {
                if (r)return (r = rt(n, t), hi.test(r) ? i(n).position()[t] + "px" : r)
            })
        }), i.each({Height: "height", Width: "width"}, function (n, t) {
            i.each({padding: "inner" + n, content: t, "": "outer" + n}, function (r, u) {
                i.fn[u] = function (u, f) {
                    var e = arguments.length && (r || "boolean" != typeof u), o = r || (u === !0 || f === !0 ? "margin" : "border");
                    return l(this, function (t, r, u) {
                        var f;
                        return i.isWindow(t) ? t.document.documentElement["client" + n] : 9 === t.nodeType ? (f = t.documentElement, Math.max(t.body["scroll" + n], f["scroll" + n], t.body["offset" + n], f["offset" + n], f["client" + n])) : void 0 === u ? i.css(t, r, o) : i.style(t, r, u, o)
                    }, t, e ? u : void 0, e, null)
                }
            })
        }), i.fn.size = function () {
            return this.length
        }, i.fn.andSelf = i.fn.addBack, "function" == typeof define && define.amd && define("jquery", [], function () {
            return i
        }), du = n.jQuery, gu = n.$, i.noConflict = function (t) {
            return n.$ === i && (n.$ = gu), t && n.jQuery === i && (n.jQuery = du), i
        }, typeof t === d && (n.jQuery = n.$ = i), i
    }), window.Modernizr = function (n, t, i) {
        function a(n) {
            c.cssText = n
        }

        function vt(n, t) {
            return a(y.join(n + ";") + (t || ""))
        }

        function h(n, t) {
            return typeof n === t
        }

        function v(n, t) {
            return !!~("" + n).indexOf(t)
        }

        function lt(n, t) {
            var u, r;
            for (u in n)if (r = n[u], !v(r, "-") && c[r] !== i)return t == "pfx" ? r : !0;
            return !1
        }

        function yt(n, t, r) {
            var f, u;
            for (f in n)if (u = t[n[f]], u !== i)return r === !1 ? n[f] : h(u, "function") ? u.bind(r || t) : u;
            return !1
        }

        function f(n, t, i) {
            var r = n.charAt(0).toUpperCase() + n.slice(1), u = (n + " " + ot.join(r + " ") + r).split(" ");
            return h(t, "string") || h(t, "undefined") ? lt(u, t) : (u = (n + " " + st.join(r + " ") + r).split(" "), yt(u, t, i))
        }

        function pt() {
            u.input = function (i) {
                for (var r = 0, u = i.length; r < u; r++)w[i[r]] = !!(i[r]in o);
                return w.list && (w.list = !!(t.createElement("datalist") && n.HTMLDataListElement)), w
            }("autocomplete autofocus list placeholder max min multiple pattern required step".split(" "));
            u.inputtypes = function (n) {
                for (var u = 0, r, f, e, h = n.length; u < h; u++)o.setAttribute("type", f = n[u]), r = o.type !== "text", r && (o.value = g, o.style.cssText = "position:absolute;visibility:hidden;", /^range$/.test(f) && o.style.WebkitAppearance !== i ? (s.appendChild(o), e = t.defaultView, r = e.getComputedStyle && e.getComputedStyle(o, null).WebkitAppearance !== "textfield" && o.offsetHeight !== 0, s.removeChild(o)) : /^(search|tel)$/.test(f) || (r = /^(url|email)$/.test(f) ? o.checkValidity && o.checkValidity() === !1 : o.value != g)), ht[n[u]] = !!r;
                return ht
            }("search tel url email datetime date month week time datetime-local number range color".split(" "))
        }

        var u = {}, d = !0, s = t.documentElement, e = "modernizr", ut = t.createElement(e), c = ut.style, o = t.createElement("input"), g = ":)", ft = {}.toString, y = " -webkit- -moz- -o- -ms- ".split(" "), et = "Webkit Moz O ms", ot = et.split(" "), st = et.toLowerCase().split(" "), p = {svg: "http://www.w3.org/2000/svg"}, r = {}, ht = {}, w = {}, nt = [], tt = nt.slice, b, l = function (n, i, r, u) {
            var l, a, c, v, f = t.createElement("div"), h = t.body, o = h || t.createElement("body");
            if (parseInt(r, 10))while (r--)c = t.createElement("div"), c.id = u ? u[r] : e + (r + 1), f.appendChild(c);
            return l = ["&#173;", '<style id="s', e, '">', n, "<\/style>"].join(""), f.id = e, (h ? f : o).innerHTML += l, o.appendChild(f), h || (o.style.background = "", o.style.overflow = "hidden", v = s.style.overflow, s.style.overflow = "hidden", s.appendChild(o)), a = i(f, n), h ? f.parentNode.removeChild(f) : (o.parentNode.removeChild(o), s.style.overflow = v), !!a
        }, at = function (t) {
            var i = n.matchMedia || n.msMatchMedia, r;
            return i ? i(t).matches : (l("@media " + t + " { #" + e + " { position: absolute; } }", function (t) {
                r = (n.getComputedStyle ? getComputedStyle(t, null) : t.currentStyle).position == "absolute"
            }), r)
        }, ct = function () {
            function r(r, u) {
                u = u || t.createElement(n[r] || "div");
                r = "on" + r;
                var f = r in u;
                return f || (u.setAttribute || (u = t.createElement("div")), u.setAttribute && u.removeAttribute && (u.setAttribute(r, ""), f = h(u[r], "function"), h(u[r], "undefined") || (u[r] = i), u.removeAttribute(r))), u = null, f
            }

            var n = {
                select: "input",
                change: "input",
                submit: "form",
                reset: "form",
                error: "img",
                load: "img",
                abort: "img"
            };
            return r
        }(), it = {}.hasOwnProperty, rt, k;
        rt = h(it, "undefined") || h(it.call, "undefined") ? function (n, t) {
            return t in n && h(n.constructor.prototype[t], "undefined")
        } : function (n, t) {
            return it.call(n, t)
        };
        Function.prototype.bind || (Function.prototype.bind = function (n) {
            var t = this, i, r;
            if (typeof t != "function")throw new TypeError;
            return i = tt.call(arguments, 1), r = function () {
                var f, e, u;
                return this instanceof r ? (f = function () {
                }, f.prototype = t.prototype, e = new f, u = t.apply(e, i.concat(tt.call(arguments))), Object(u) === u) ? u : e : t.apply(n, i.concat(tt.call(arguments)))
            }, r
        });
        r.flexbox = function () {
            return f("flexWrap")
        };
        r.flexboxlegacy = function () {
            return f("boxDirection")
        };
        r.canvas = function () {
            var n = t.createElement("canvas");
            return !!(n.getContext && n.getContext("2d"))
        };
        r.canvastext = function () {
            return !!(u.canvas && h(t.createElement("canvas").getContext("2d").fillText, "function"))
        };
        r.webgl = function () {
            return !!n.WebGLRenderingContext
        };
        r.touch = function () {
            var i;
            return "ontouchstart"in n || n.DocumentTouch && t instanceof DocumentTouch ? i = !0 : l(["@media (", y.join("touch-enabled),("), e, ")", "{#modernizr{top:9px;position:absolute}}"].join(""), function (n) {
                i = n.offsetTop === 9
            }), i
        };
        r.geolocation = function () {
            return "geolocation"in navigator
        };
        r.postmessage = function () {
            return !!n.postMessage
        };
        r.websqldatabase = function () {
            return !!n.openDatabase
        };
        r.indexedDB = function () {
            return !!f("indexedDB", n)
        };
        r.hashchange = function () {
            return ct("hashchange", n) && (t.documentMode === i || t.documentMode > 7)
        };
        r.history = function () {
            return !!(n.history && history.pushState)
        };
        r.draganddrop = function () {
            var n = t.createElement("div");
            return "draggable"in n || "ondragstart"in n && "ondrop"in n
        };
        r.websockets = function () {
            return "WebSocket"in n || "MozWebSocket"in n
        };
        r.rgba = function () {
            return a("background-color:rgba(150,255,150,.5)"), v(c.backgroundColor, "rgba")
        };
        r.hsla = function () {
            return a("background-color:hsla(120,40%,100%,.5)"), v(c.backgroundColor, "rgba") || v(c.backgroundColor, "hsla")
        };
        r.multiplebgs = function () {
            return a("background:url(https://),url(https://),red url(https://)"), /(url\s*\(.*?){3}/.test(c.background)
        };
        r.backgroundsize = function () {
            return f("backgroundSize")
        };
        r.borderimage = function () {
            return f("borderImage")
        };
        r.borderradius = function () {
            return f("borderRadius")
        };
        r.boxshadow = function () {
            return f("boxShadow")
        };
        r.textshadow = function () {
            return t.createElement("div").style.textShadow === ""
        };
        r.opacity = function () {
            return vt("opacity:.55"), /^0.55$/.test(c.opacity)
        };
        r.cssanimations = function () {
            return f("animationName")
        };
        r.csscolumns = function () {
            return f("columnCount")
        };
        r.cssgradients = function () {
            var n = "background-image:";
            return a((n + "-webkit- ".split(" ").join("gradient(linear,left top,right bottom,from(#9f9),to(white));" + n) + y.join("linear-gradient(left top,#9f9, white);" + n)).slice(0, -n.length)), v(c.backgroundImage, "gradient")
        };
        r.cssreflections = function () {
            return f("boxReflect")
        };
        r.csstransforms = function () {
            return !!f("transform")
        };
        r.csstransforms3d = function () {
            var n = !!f("perspective");
            return n && "webkitPerspective"in s.style && l("@media (transform-3d),(-webkit-transform-3d){#modernizr{left:9px;position:absolute;height:3px;}}", function (t) {
                n = t.offsetLeft === 9 && t.offsetHeight === 3
            }), n
        };
        r.csstransitions = function () {
            return f("transition")
        };
        r.fontface = function () {
            var n;
            return l('@font-face {font-family:"font";src:url("https://")}', function (i, r) {
                var f = t.getElementById("smodernizr"), u = f.sheet || f.styleSheet, e = u ? u.cssRules && u.cssRules[0] ? u.cssRules[0].cssText : u.cssText || "" : "";
                n = /src/i.test(e) && e.indexOf(r.split(" ")[0]) === 0
            }), n
        };
        r.generatedcontent = function () {
            var n;
            return l(["#", e, "{font:0/0 a}#", e, ':after{content:"', g, '";visibility:hidden;font:3px/1 a}'].join(""), function (t) {
                n = t.offsetHeight >= 3
            }), n
        };
        r.video = function () {
            var i = t.createElement("video"), n = !1;
            try {
                (n = !!i.canPlayType) && (n = new Boolean(n), n.ogg = i.canPlayType('video/ogg; codecs="theora"').replace(/^no$/, ""), n.h264 = i.canPlayType('video/mp4; codecs="avc1.42E01E"').replace(/^no$/, ""), n.webm = i.canPlayType('video/webm; codecs="vp8, vorbis"').replace(/^no$/, ""))
            } catch (r) {
            }
            return n
        };
        r.audio = function () {
            var i = t.createElement("audio"), n = !1;
            try {
                (n = !!i.canPlayType) && (n = new Boolean(n), n.ogg = i.canPlayType('audio/ogg; codecs="vorbis"').replace(/^no$/, ""), n.mp3 = i.canPlayType("audio/mpeg;").replace(/^no$/, ""), n.wav = i.canPlayType('audio/wav; codecs="1"').replace(/^no$/, ""), n.m4a = (i.canPlayType("audio/x-m4a;") || i.canPlayType("audio/aac;")).replace(/^no$/, ""))
            } catch (r) {
            }
            return n
        };
        r.localstorage = function () {
            try {
                return localStorage.setItem(e, e), localStorage.removeItem(e), !0
            } catch (n) {
                return !1
            }
        };
        r.sessionstorage = function () {
            try {
                return sessionStorage.setItem(e, e), sessionStorage.removeItem(e), !0
            } catch (n) {
                return !1
            }
        };
        r.webworkers = function () {
            return !!n.Worker
        };
        r.applicationcache = function () {
            return !!n.applicationCache
        };
        r.svg = function () {
            return !!t.createElementNS && !!t.createElementNS(p.svg, "svg").createSVGRect
        };
        r.inlinesvg = function () {
            var n = t.createElement("div");
            return n.innerHTML = "<svg/>", (n.firstChild && n.firstChild.namespaceURI) == p.svg
        };
        r.smil = function () {
            return !!t.createElementNS && /SVGAnimate/.test(ft.call(t.createElementNS(p.svg, "animate")))
        };
        r.svgclippaths = function () {
            return !!t.createElementNS && /SVGClipPath/.test(ft.call(t.createElementNS(p.svg, "clipPath")))
        };
        for (k in r)rt(r, k) && (b = k.toLowerCase(), u[b] = r[k](), nt.push((u[b] ? "" : "no-") + b));
        return u.input || pt(), u.addTest = function (n, t) {
            if (typeof n == "object")for (var r in n)rt(n, r) && u.addTest(r, n[r]); else {
                if (n = n.toLowerCase(), u[n] !== i)return u;
                t = typeof t == "function" ? t() : t;
                typeof d != "undefined" && d && (s.className += " " + (t ? "" : "no-") + n);
                u[n] = t
            }
            return u
        }, a(""), ut = o = null, function (n, t) {
            function p(n, t) {
                var i = n.createElement("p"), r = n.getElementsByTagName("head")[0] || n.documentElement;
                return i.innerHTML = "x<style>" + t + "<\/style>", r.insertBefore(i.lastChild, r.firstChild)
            }

            function c() {
                var n = r.elements;
                return typeof n == "string" ? n.split(" ") : n
            }

            function o(n) {
                var t = h[n[s]];
                return t || (t = {}, e++, n[s] = e, h[e] = t), t
            }

            function l(n, r, u) {
                if (r || (r = t), i)return r.createElement(n);
                u || (u = o(r));
                var f;
                return f = u.cache[n] ? u.cache[n].cloneNode() : y.test(n) ? (u.cache[n] = u.createElem(n)).cloneNode() : u.createElem(n), f.canHaveChildren && !v.test(n) ? u.frag.appendChild(f) : f
            }

            function w(n, r) {
                if (n || (n = t), i)return n.createDocumentFragment();
                r = r || o(n);
                for (var f = r.frag.cloneNode(), u = 0, e = c(), s = e.length; u < s; u++)f.createElement(e[u]);
                return f
            }

            function b(n, t) {
                t.cache || (t.cache = {}, t.createElem = n.createElement, t.createFrag = n.createDocumentFragment, t.frag = t.createFrag());
                n.createElement = function (i) {
                    return r.shivMethods ? l(i, n, t) : t.createElem(i)
                };
                n.createDocumentFragment = Function("h,f", "return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&(" + c().join().replace(/\w+/g, function (n) {
                        return t.createElem(n), t.frag.createElement(n), 'c("' + n + '")'
                    }) + ");return n}")(r, t.frag)
            }

            function a(n) {
                n || (n = t);
                var u = o(n);
                return !r.shivCSS || f || u.hasCSS || (u.hasCSS = !!p(n, "article,aside,figcaption,figure,footer,header,hgroup,nav,section{display:block}mark{background:#FF0;color:#000}")), i || b(n, u), n
            }

            var u = n.html5 || {}, v = /^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i, y = /^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i, f, s = "_html5shiv", e = 0, h = {}, i, r;
            (function () {
                try {
                    var n = t.createElement("a");
                    n.innerHTML = "<xyz><\/xyz>";
                    f = "hidden"in n;
                    i = n.childNodes.length == 1 || function () {
                            t.createElement("a");
                            var n = t.createDocumentFragment();
                            return typeof n.cloneNode == "undefined" || typeof n.createDocumentFragment == "undefined" || typeof n.createElement == "undefined"
                        }()
                } catch (r) {
                    f = !0;
                    i = !0
                }
            })();
            r = {
                elements: u.elements || "abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",
                shivCSS: u.shivCSS !== !1,
                supportsUnknownElements: i,
                shivMethods: u.shivMethods !== !1,
                type: "default",
                shivDocument: a,
                createElement: l,
                createDocumentFragment: w
            };
            n.html5 = r;
            a(t)
        }(this, t), u._version = "2.6.2", u._prefixes = y, u._domPrefixes = st, u._cssomPrefixes = ot, u.mq = at, u.hasEvent = ct, u.testProp = function (n) {
            return lt([n])
        }, u.testAllProps = f, u.testStyles = l, u.prefixed = function (n, t, i) {
            return t ? f(n, t, i) : f(n, "pfx")
        }, s.className = s.className.replace(/(^|\s)no-js(\s|$)/, "$1$2") + (d ? " js " + nt.join(" ") : ""), u
    }(this, this.document), function (n) {
        var e = !1, h = !1, y = 5e3, p = 2e3, r = 0, w = function () {
            var n = document.getElementsByTagName("script"), n = n[n.length - 1].src.split("?")[0];
            return 0 < n.split("/").length ? n.split("/").slice(0, -1).join("/") + "/" : ""
        }(), t, i, u, f;
        Array.prototype.forEach || (Array.prototype.forEach = function (n, t) {
            for (var i = 0, r = this.length; i < r; ++i)n.call(t, this[i], i, this)
        });
        t = window.requestAnimationFrame || !1;
        i = window.cancelAnimationFrame || !1;
        ["ms", "moz", "webkit", "o"].forEach(function (n) {
            t || (t = window[n + "RequestAnimationFrame"]);
            i || (i = window[n + "CancelAnimationFrame"] || window[n + "CancelRequestAnimationFrame"])
        });
        var o = window.MutationObserver || window.WebKitMutationObserver || !1, l = {
            zindex: "auto",
            cursoropacitymin: 0,
            cursoropacitymax: 1,
            cursorcolor: "#424242",
            cursorwidth: "5px",
            cursorborder: "1px solid #fff",
            cursorborderradius: "5px",
            scrollspeed: 60,
            mousescrollstep: 24,
            touchbehavior: !1,
            hwacceleration: !0,
            usetransition: !0,
            boxzoom: !1,
            dblclickzoom: !0,
            gesturezoom: !0,
            grabcursorenabled: !0,
            autohidemode: !0,
            background: "",
            iframeautoresize: !0,
            cursorminheight: 32,
            preservenativescrolling: !0,
            railoffset: !1,
            bouncescroll: !0,
            spacebarenabled: !0,
            railpadding: {top: 0, right: 0, left: 0, bottom: 0},
            disableoutline: !0,
            horizrailenabled: !0,
            railalign: "right",
            railvalign: "bottom",
            enabletranslate3d: !0,
            enablemousewheel: !0,
            enablekeyboard: !0,
            smoothscroll: !0,
            sensitiverail: !0,
            enablemouselockapi: !0,
            cursorfixedheight: !1,
            directionlockdeadzone: 6,
            hidecursordelay: 400,
            nativeparentscrolling: !0,
            enablescrollonselection: !0,
            overflowx: !0,
            overflowy: !0,
            cursordragspeed: .3,
            rtlmode: !1,
            cursordragontouch: !1
        }, c = !1, b = function () {
            var r, n, i, t;
            if (c)return c;
            for (r = document.createElement("DIV"), n = {haspointerlock: "pointerLockElement"in document || "mozPointerLockElement"in document || "webkitPointerLockElement"in document}, n.isopera = ("opera"in window), n.isopera12 = n.isopera && ("getUserMedia"in navigator), n.isie = ("all"in document) && ("attachEvent"in r) && !n.isopera, n.isieold = n.isie && !("msInterpolationMode"in r.style), n.isie7 = n.isie && !n.isieold && (!("documentMode"in document) || 7 == document.documentMode), n.isie8 = n.isie && ("documentMode"in document) && 8 == document.documentMode, n.isie9 = n.isie && ("performance"in window) && 9 <= document.documentMode, n.isie10 = n.isie && ("performance"in window) && 10 <= document.documentMode, n.isie9mobile = /iemobile.9/i.test(navigator.userAgent), n.isie9mobile && (n.isie9 = !1), n.isie7mobile = !n.isie9mobile && n.isie7 && /iemobile/i.test(navigator.userAgent), n.ismozilla = ("MozAppearance"in r.style), n.iswebkit = ("WebkitAppearance"in r.style), n.ischrome = ("chrome"in window), n.ischrome22 = n.ischrome && n.haspointerlock, n.ischrome26 = n.ischrome && ("transition"in r.style), n.cantouch = ("ontouchstart"in document.documentElement) || ("ontouchstart"in window), n.hasmstouch = window.navigator.msPointerEnabled || !1, n.ismac = /^mac$/i.test(navigator.platform), n.isios = n.cantouch && /iphone|ipad|ipod/i.test(navigator.platform), n.isios4 = n.isios && !("seal"in Object), n.isandroid = /android/i.test(navigator.userAgent), n.trstyle = !1, n.hastransform = !1, n.hastranslate3d = !1, n.transitionstyle = !1, n.hastransition = !1, n.transitionend = !1, i = ["transform", "msTransform", "webkitTransform", "MozTransform", "OTransform"], t = 0; t < i.length; t++)if ("undefined" != typeof r.style[i[t]]) {
                n.trstyle = i[t];
                break
            }
            n.hastransform = !1 != n.trstyle;
            n.hastransform && (r.style[n.trstyle] = "translate3d(1px,2px,3px)", n.hastranslate3d = /translate3d/.test(r.style[n.trstyle]));
            n.transitionstyle = !1;
            n.prefixstyle = "";
            n.transitionend = !1;
            for (var i = "transition webkitTransition MozTransition OTransition OTransition msTransition KhtmlTransition".split(" "), u = " -webkit- -moz- -o- -o -ms- -khtml-".split(" "), f = "transitionend webkitTransitionEnd transitionend otransitionend oTransitionEnd msTransitionEnd KhtmlTransitionEnd".split(" "), t = 0; t < i.length; t++)if (i[t]in r.style) {
                n.transitionstyle = i[t];
                n.prefixstyle = u[t];
                n.transitionend = f[t];
                break
            }
            n.ischrome26 && (n.prefixstyle = u[1]);
            n.hastransition = n.transitionstyle;
            n:{
                for (i = ["-moz-grab", "-webkit-grab", "grab"], (n.ischrome && !n.ischrome22 || n.isie) && (i = []), t = 0; t < i.length; t++)if (u = i[t], r.style.cursor = u, r.style.cursor == u) {
                    i = u;
                    break n
                }
                i = "url(http://www.google.com/intl/en_ALL/mapfiles/openhand.cur),n-resize"
            }
            return n.cursorgrabvalue = i, n.hasmousecapture = "setCapture"in r, n.hasMutationObserver = !1 !== o, c = n
        }, k = function (u, f) {
            function it() {
                var n = s.win, t;
                if ("zIndex"in n)return n.zIndex();
                for (; 0 < n.length && 9 != n[0].nodeType;) {
                    if (t = n.css("zIndex"), !isNaN(t) && 0 != t)return parseInt(t);
                    n = n.parent()
                }
                return !1
            }

            function a(n, t, i) {
                return t = n.css(t), n = parseFloat(t), isNaN(n) ? (n = tt[t] || 0, i = 3 == n ? i ? s.win.outerHeight() - s.win.innerHeight() : s.win.outerWidth() - s.win.innerWidth() : 1, s.isie8 && n && (n += 1), i ? n : 0) : n
            }

            function g(n, t, i, r) {
                s._bind(n, t, function (r) {
                    r = r ? r : window.event;
                    var u = {
                        original: r,
                        target: r.target || r.srcElement,
                        type: "wheel",
                        deltaMode: "MozMousePixelScroll" == r.type ? 0 : 1,
                        deltaX: 0,
                        deltaZ: 0,
                        preventDefault: function () {
                            return r.preventDefault ? r.preventDefault() : r.returnValue = !1, !1
                        },
                        stopImmediatePropagation: function () {
                            r.stopImmediatePropagation ? r.stopImmediatePropagation() : r.cancelBubble = !0
                        }
                    };
                    return "mousewheel" == t ? (u.deltaY = -.025 * r.wheelDelta, r.wheelDeltaX && (u.deltaX = -.025 * r.wheelDeltaX)) : u.deltaY = r.detail, i.call(n, u)
                }, r)
            }

            function nt(n, t, i) {
                var u, r;
                if (0 == n.deltaMode ? (u = -Math.floor(n.deltaX * (s.opt.mousescrollstep / 54)), r = -Math.floor(n.deltaY * (s.opt.mousescrollstep / 54))) : 1 == n.deltaMode && (u = -Math.floor(n.deltaX * s.opt.mousescrollstep), r = -Math.floor(n.deltaY * s.opt.mousescrollstep)), t && 0 == u && r && (u = r, r = 0), u && (s.scrollmom && s.scrollmom.stop(), s.lastdeltax += u, s.debounced("mousewheelx", function () {
                        var n = s.lastdeltax;
                        s.lastdeltax = 0;
                        s.rail.drag || s.doScrollLeftBy(n)
                    }, 120)), r) {
                    if (s.opt.nativeparentscrolling && i && !s.ispage && !s.zoomactive)if (0 > r) {
                        if (s.getScrollTop() >= s.page.maxh)return !0
                    } else if (0 >= s.getScrollTop())return !0;
                    s.scrollmom && s.scrollmom.stop();
                    s.lastdeltay += r;
                    s.debounced("mousewheely", function () {
                        var n = s.lastdeltay;
                        s.lastdeltay = 0;
                        s.rail.drag || s.doScrollBy(n)
                    }, 120)
                }
                return n.stopImmediatePropagation(), n.preventDefault()
            }

            var s = this, k, c, d, tt;
            if (this.version = "3.4.0", this.name = "nicescroll", this.me = f, this.opt = {
                    doc: n("body"),
                    win: !1
                }, n.extend(this.opt, l), this.opt.snapbackspeed = 80, u)for (k in s.opt)"undefined" != typeof u[k] && (s.opt[k] = u[k]);
            this.iddoc = (this.doc = s.opt.doc) && this.doc[0] ? this.doc[0].id || "" : "";
            this.ispage = /BODY|HTML/.test(s.opt.win ? s.opt.win[0].nodeName : this.doc[0].nodeName);
            this.haswrapper = !1 !== s.opt.win;
            this.win = s.opt.win || (this.ispage ? n(window) : this.doc);
            this.docscroll = this.ispage && !this.haswrapper ? n(window) : this.win;
            this.body = n("body");
            this.iframe = this.isfixed = this.viewport = !1;
            this.isiframe = "IFRAME" == this.doc[0].nodeName && "IFRAME" == this.win[0].nodeName;
            this.istextarea = "TEXTAREA" == this.win[0].nodeName;
            this.forcescreen = !1;
            this.canshowonmouseevent = "scroll" != s.opt.autohidemode;
            this.page = this.view = this.onzoomout = this.onzoomin = this.onscrollcancel = this.onscrollend = this.onscrollstart = this.onclick = this.ongesturezoom = this.onkeypress = this.onmousewheel = this.onmousemove = this.onmouseup = this.onmousedown = !1;
            this.scroll = {x: 0, y: 0};
            this.scrollratio = {x: 0, y: 0};
            this.cursorheight = 20;
            this.scrollvaluemax = 0;
            this.observerremover = this.observer = this.scrollmom = this.scrollrunning = this.checkrtlmode = !1;
            do this.id = "ascrail" + p++; while (document.getElementById(this.id));
            this.hasmousefocus = this.hasfocus = this.zoomactive = this.zoom = this.selectiondrag = this.cursorfreezed = this.cursor = this.rail = !1;
            this.visibility = !0;
            this.hidden = this.locked = !1;
            this.cursoractive = !0;
            this.overflowx = s.opt.overflowx;
            this.overflowy = s.opt.overflowy;
            this.nativescrollingarea = !1;
            this.checkarea = 0;
            this.events = [];
            this.saved = {};
            this.delaylist = {};
            this.synclist = {};
            this.lastdeltay = this.lastdeltax = 0;
            this.detected = b();
            c = n.extend({}, this.detected);
            this.ishwscroll = (this.canhwscroll = c.hastransform && s.opt.hwacceleration) && s.haswrapper;
            this.istouchcapable = !1;
            c.cantouch && c.ischrome && !c.isios && !c.isandroid && (this.istouchcapable = !0, c.cantouch = !1);
            c.cantouch && c.ismozilla && !c.isios && (this.istouchcapable = !0, c.cantouch = !1);
            s.opt.enablemouselockapi || (c.hasmousecapture = !1, c.haspointerlock = !1);
            this.delayed = function (n, t, i, r) {
                var u = s.delaylist[n], f = (new Date).getTime();
                if (!r && u && u.tt)return !1;
                u && u.tt && clearTimeout(u.tt);
                u && u.last + i > f && !u.tt ? s.delaylist[n] = {
                    last: f + i, tt: setTimeout(function () {
                        s.delaylist[n].tt = 0;
                        t.call()
                    }, i)
                } : u && u.tt || (s.delaylist[n] = {last: f, tt: 0}, setTimeout(function () {
                    t.call()
                }, 0))
            };
            this.debounced = function (n, t, i) {
                var r = s.delaylist[n];
                (new Date).getTime();
                s.delaylist[n] = t;
                r || setTimeout(function () {
                    var t = s.delaylist[n];
                    s.delaylist[n] = !1;
                    t.call()
                }, i)
            };
            this.synched = function (n, i) {
                return s.synclist[n] = i, function () {
                    s.onsync || (t(function () {
                        s.onsync = !1;
                        for (n in s.synclist) {
                            var t = s.synclist[n];
                            t && t.call(s);
                            s.synclist[n] = !1
                        }
                    }), s.onsync = !0)
                }(), n
            };
            this.unsynched = function (n) {
                s.synclist[n] && (s.synclist[n] = !1)
            };
            this.css = function (n, t) {
                for (var i in t)s.saved.css.push([n, i, n.css(i)]), n.css(i, t[i])
            };
            this.scrollTop = function (n) {
                return "undefined" == typeof n ? s.getScrollTop() : s.setScrollTop(n)
            };
            this.scrollLeft = function (n) {
                return "undefined" == typeof n ? s.getScrollLeft() : s.setScrollLeft(n)
            };
            BezierClass = function (n, t, i, r, u, f, e) {
                this.st = n;
                this.ed = t;
                this.spd = i;
                this.p1 = r || 0;
                this.p2 = u || 1;
                this.p3 = f || 0;
                this.p4 = e || 1;
                this.ts = (new Date).getTime();
                this.df = this.ed - this.st
            };
            BezierClass.prototype = {
                B2: function (n) {
                    return 3 * n * n * (1 - n)
                }, B3: function (n) {
                    return 3 * n * (1 - n) * (1 - n)
                }, B4: function (n) {
                    return (1 - n) * (1 - n) * (1 - n)
                }, getNow: function () {
                    var n = 1 - ((new Date).getTime() - this.ts) / this.spd, t = this.B2(n) + this.B3(n) + this.B4(n);
                    return 0 > n ? this.ed : this.st + Math.round(this.df * t)
                }, update: function (n, t) {
                    return this.st = this.getNow(), this.ed = n, this.spd = t, this.ts = (new Date).getTime(), this.df = this.ed - this.st, this
                }
            };
            this.ishwscroll ? (this.doc.translate = {
                x: 0,
                y: 0,
                tx: "0px",
                ty: "0px"
            }, c.hastranslate3d && c.isios && this.doc.css("-webkit-backface-visibility", "hidden"), d = function () {
                var n = s.doc.css(c.trstyle);
                return n && "matrix" == n.substr(0, 6) ? n.replace(/^.*\((.*)\)$/g, "$1").replace(/px/g, "").split(/, +/) : !1
            }, this.getScrollTop = function (n) {
                if (!n) {
                    if (n = d())return 16 == n.length ? -n[13] : -n[5];
                    if (s.timerscroll && s.timerscroll.bz)return s.timerscroll.bz.getNow()
                }
                return s.doc.translate.y
            }, this.getScrollLeft = function (n) {
                if (!n) {
                    if (n = d())return 16 == n.length ? -n[12] : -n[4];
                    if (s.timerscroll && s.timerscroll.bh)return s.timerscroll.bh.getNow()
                }
                return s.doc.translate.x
            }, this.notifyScrollEvent = document.createEvent ? function (n) {
                var t = document.createEvent("UIEvents");
                t.initUIEvent("scroll", !1, !0, window, 1);
                n.dispatchEvent(t)
            } : document.fireEvent ? function (n) {
                var t = document.createEventObject();
                n.fireEvent("onscroll");
                t.cancelBubble = !0
            } : function () {
            }, c.hastranslate3d && s.opt.enabletranslate3d ? (this.setScrollTop = function (n, t) {
                s.doc.translate.y = n;
                s.doc.translate.ty = -1 * n + "px";
                s.doc.css(c.trstyle, "translate3d(" + s.doc.translate.tx + "," + s.doc.translate.ty + ",0px)");
                t || s.notifyScrollEvent(s.win[0])
            }, this.setScrollLeft = function (n, t) {
                s.doc.translate.x = n;
                s.doc.translate.tx = -1 * n + "px";
                s.doc.css(c.trstyle, "translate3d(" + s.doc.translate.tx + "," + s.doc.translate.ty + ",0px)");
                t || s.notifyScrollEvent(s.win[0])
            }) : (this.setScrollTop = function (n, t) {
                s.doc.translate.y = n;
                s.doc.translate.ty = -1 * n + "px";
                s.doc.css(c.trstyle, "translate(" + s.doc.translate.tx + "," + s.doc.translate.ty + ")");
                t || s.notifyScrollEvent(s.win[0])
            }, this.setScrollLeft = function (n, t) {
                s.doc.translate.x = n;
                s.doc.translate.tx = -1 * n + "px";
                s.doc.css(c.trstyle, "translate(" + s.doc.translate.tx + "," + s.doc.translate.ty + ")");
                t || s.notifyScrollEvent(s.win[0])
            })) : (this.getScrollTop = function () {
                return s.docscroll.scrollTop()
            }, this.setScrollTop = function (n) {
                return s.docscroll.scrollTop(n)
            }, this.getScrollLeft = function () {
                return s.docscroll.scrollLeft()
            }, this.setScrollLeft = function (n) {
                return s.docscroll.scrollLeft(n)
            });
            this.getTarget = function (n) {
                return n ? n.target ? n.target : n.srcElement ? n.srcElement : !1 : !1
            };
            this.hasParent = function (n, t) {
                if (!n)return !1;
                for (var i = n.target || n.srcElement || n || !1; i && i.id != t;)i = i.parentNode || !1;
                return !1 !== i
            };
            tt = {thin: 1, medium: 3, thick: 5};
            this.getOffset = function () {
                if (s.isfixed)return {top: parseFloat(s.win.css("top")), left: parseFloat(s.win.css("left"))};
                if (!s.viewport)return s.win.offset();
                var n = s.win.offset(), t = s.viewport.offset();
                return {top: n.top - t.top + s.viewport.scrollTop(), left: n.left - t.left + s.viewport.scrollLeft()}
            };
            this.updateScrollBar = function (n) {
                var t, r;
                if (s.ishwscroll)s.rail.css({height: s.win.innerHeight()}), s.railh && s.railh.css({width: s.win.innerWidth()}); else {
                    var u = s.getOffset(), i = u.top, t = u.left, i = i + a(s.win, "border-top-width", !0);
                    s.win.outerWidth();
                    s.win.innerWidth();
                    t = t + (s.rail.align ? s.win.outerWidth() - a(s.win, "border-right-width") - s.rail.width : a(s.win, "border-left-width"));
                    r = s.opt.railoffset;
                    r && (r.top && (i += r.top), s.rail.align && r.left && (t += r.left));
                    s.locked || s.rail.css({top: i, left: t, height: n ? n.h : s.win.innerHeight()});
                    s.zoom && s.zoom.css({top: i + 1, left: 1 == s.rail.align ? t - 20 : t + s.rail.width + 4});
                    s.railh && !s.locked && (i = u.top, t = u.left, n = s.railh.align ? i + a(s.win, "border-top-width", !0) + s.win.innerHeight() - s.railh.height : i + a(s.win, "border-top-width", !0), t += a(s.win, "border-left-width"), s.railh.css({
                        top: n,
                        left: t,
                        width: s.railh.width
                    }))
                }
            };
            this.doRailClick = function (n, t, i) {
                var r;
                s.locked || (s.cancelEvent(n), t ? (t = i ? s.doScrollLeft : s.doScrollTop, r = i ? (n.pageX - s.railh.offset().left - s.cursorwidth / 2) * s.scrollratio.x : (n.pageY - s.rail.offset().top - s.cursorheight / 2) * s.scrollratio.y, t(r)) : (t = i ? s.doScrollLeftBy : s.doScrollBy, r = i ? s.scroll.x : s.scroll.y, n = i ? n.pageX - s.railh.offset().left : n.pageY - s.rail.offset().top, i = i ? s.view.w : s.view.h, r >= n ? t(i) : t(-i)))
            };
            s.hasanimationframe = t;
            s.hascancelanimationframe = i;
            s.hasanimationframe ? s.hascancelanimationframe || (i = function () {
                s.cancelAnimationFrame = !0
            }) : (t = function (n) {
                return setTimeout(n, 15 - Math.floor(+new Date / 1e3) % 16)
            }, i = clearInterval);
            this.init = function () {
                var f, u, k, l, a, d, i, g, p, nt, t, b;
                if (s.saved.css = [], c.isie7mobile)return !0;
                if (c.hasmstouch && s.css(s.ispage ? n("html") : s.win, {"-ms-touch-action": "none"}), s.zindex = "auto", s.zindex = !s.ispage && "auto" == s.opt.zindex ? it() || "auto" : s.opt.zindex, !s.ispage && "auto" != s.zindex && s.zindex > r && (r = s.zindex), s.isie && 0 == s.zindex && "auto" == s.opt.zindex && (s.zindex = "auto"), !s.ispage || !c.cantouch && !c.isieold && !c.isie9mobile) {
                    f = s.docscroll;
                    s.ispage && (f = s.haswrapper ? s.win : s.doc);
                    c.isie9mobile || s.css(f, {"overflow-y": "hidden"});
                    s.ispage && c.isie7 && ("BODY" == s.doc[0].nodeName ? s.css(n("html"), {"overflow-y": "hidden"}) : "HTML" == s.doc[0].nodeName && s.css(n("body"), {"overflow-y": "hidden"}));
                    !c.isios || s.ispage || s.haswrapper || s.css(n("body"), {"-webkit-overflow-scrolling": "touch"});
                    u = n(document.createElement("div"));
                    u.css({
                        position: "relative",
                        top: 0,
                        float: "right",
                        width: s.opt.cursorwidth,
                        height: "0px",
                        "background-color": s.opt.cursorcolor,
                        border: s.opt.cursorborder,
                        "background-clip": "padding-box",
                        "-webkit-border-radius": s.opt.cursorborderradius,
                        "-moz-border-radius": s.opt.cursorborderradius,
                        "border-radius": s.opt.cursorborderradius
                    });
                    u.hborder = parseFloat(u.outerHeight() - u.innerHeight());
                    s.cursor = u;
                    t = n(document.createElement("div"));
                    t.attr("id", s.id);
                    t.addClass("nicescroll-rails");
                    a = ["left", "right"];
                    for (d in a)l = a[d], (k = s.opt.railpadding[l]) ? t.css("padding-" + l, k + "px") : s.opt.railpadding[l] = 0;
                    t.append(u);
                    t.width = Math.max(parseFloat(s.opt.cursorwidth), u.outerWidth()) + s.opt.railpadding.left + s.opt.railpadding.right;
                    t.css({width: t.width + "px", zIndex: s.zindex, background: s.opt.background, cursor: "default"});
                    t.visibility = !0;
                    t.scrollable = !0;
                    t.align = "left" == s.opt.railalign ? 0 : 1;
                    s.rail = t;
                    u = s.rail.drag = !1;
                    !s.opt.boxzoom || s.ispage || c.isieold || (u = document.createElement("div"), s.bind(u, "click", s.doZoom), s.zoom = n(u), s.zoom.css({
                        cursor: "pointer",
                        "z-index": s.zindex,
                        backgroundImage: "url(" + w + "zoomico.png)",
                        height: 18,
                        width: 18,
                        backgroundPosition: "0px 0px"
                    }), s.opt.dblclickzoom && s.bind(s.win, "dblclick", s.doZoom), c.cantouch && s.opt.gesturezoom && (s.ongesturezoom = function (n) {
                        return 1.5 < n.scale && s.doZoomIn(n), .8 > n.scale && s.doZoomOut(n), s.cancelEvent(n)
                    }, s.bind(s.win, "gestureend", s.ongesturezoom)));
                    s.railh = !1;
                    s.opt.horizrailenabled && (s.css(f, {"overflow-x": "hidden"}), u = n(document.createElement("div")), u.css({
                        position: "relative",
                        top: 0,
                        height: s.opt.cursorwidth,
                        width: "0px",
                        "background-color": s.opt.cursorcolor,
                        border: s.opt.cursorborder,
                        "background-clip": "padding-box",
                        "-webkit-border-radius": s.opt.cursorborderradius,
                        "-moz-border-radius": s.opt.cursorborderradius,
                        "border-radius": s.opt.cursorborderradius
                    }), u.wborder = parseFloat(u.outerWidth() - u.innerWidth()), s.cursorh = u, i = n(document.createElement("div")), i.attr("id", s.id + "-hr"), i.addClass("nicescroll-rails"), i.height = Math.max(parseFloat(s.opt.cursorwidth), u.outerHeight()), i.css({
                        height: i.height + "px",
                        zIndex: s.zindex,
                        background: s.opt.background
                    }), i.append(u), i.visibility = !0, i.scrollable = !0, i.align = "top" == s.opt.railvalign ? 0 : 1, s.railh = i, s.railh.drag = !1);
                    s.ispage ? (t.css({
                        position: "fixed",
                        top: "0px",
                        height: "100%"
                    }), t.align ? t.css({right: "0px"}) : t.css({left: "0px"}), s.body.append(t), s.railh && (i.css({
                        position: "fixed",
                        left: "0px",
                        width: "100%"
                    }), i.align ? i.css({bottom: "0px"}) : i.css({top: "0px"}), s.body.append(i))) : (s.ishwscroll ? ("static" == s.win.css("position") && s.css(s.win, {position: "relative"}), f = "HTML" == s.win[0].nodeName ? s.body : s.win, s.zoom && (s.zoom.css({
                        position: "absolute",
                        top: 1,
                        right: 0,
                        "margin-right": t.width + 4
                    }), f.append(s.zoom)), t.css({
                        position: "absolute",
                        top: 0
                    }), t.align ? t.css({right: 0}) : t.css({left: 0}), f.append(t), i && (i.css({
                        position: "absolute",
                        left: 0,
                        bottom: 0
                    }), i.align ? i.css({bottom: 0}) : i.css({top: 0}), f.append(i))) : (s.isfixed = "fixed" == s.win.css("position"), f = s.isfixed ? "fixed" : "absolute", s.isfixed || (s.viewport = s.getViewport(s.win[0])), s.viewport && (s.body = s.viewport, !1 == /relative|absolute/.test(s.viewport.css("position")) && s.css(s.viewport, {position: "relative"})), t.css({position: f}), s.zoom && s.zoom.css({position: f}), s.updateScrollBar(), s.body.append(t), s.zoom && s.body.append(s.zoom), s.railh && (i.css({position: f}), s.body.append(i))), c.isios && s.css(s.win, {
                        "-webkit-tap-highlight-color": "rgba(0,0,0,0)",
                        "-webkit-touch-callout": "none"
                    }), c.isie && s.opt.disableoutline && s.win.attr("hideFocus", "true"), c.iswebkit && s.opt.disableoutline && s.win.css({outline: "none"}));
                    !1 === s.opt.autohidemode ? (s.autohidedom = !1, s.rail.css({opacity: s.opt.cursoropacitymax}), s.railh && s.railh.css({opacity: s.opt.cursoropacitymax})) : !0 === s.opt.autohidemode ? (s.autohidedom = n().add(s.rail), c.isie8 && (s.autohidedom = s.autohidedom.add(s.cursor)), s.railh && (s.autohidedom = s.autohidedom.add(s.railh)), s.railh && c.isie8 && (s.autohidedom = s.autohidedom.add(s.cursorh))) : "scroll" == s.opt.autohidemode ? (s.autohidedom = n().add(s.rail), s.railh && (s.autohidedom = s.autohidedom.add(s.railh))) : "cursor" == s.opt.autohidemode ? (s.autohidedom = n().add(s.cursor), s.railh && (s.autohidedom = s.autohidedom.add(s.cursorh))) : "hidden" == s.opt.autohidemode && (s.autohidedom = !1, s.hide(), s.locked = !1);
                    c.isie9mobile ? (s.scrollmom = new v(s), s.onmangotouch = function (n) {
                        var t, i, r;
                        if (n = s.getScrollTop(), t = s.getScrollLeft(), n == s.scrollmom.lastscrolly && t == s.scrollmom.lastscrollx)return !0;
                        if (i = n - s.mangotouch.sy, r = t - s.mangotouch.sx, 0 != Math.round(Math.sqrt(Math.pow(r, 2) + Math.pow(i, 2)))) {
                            var f = 0 > i ? -1 : 1, e = 0 > r ? -1 : 1, u = +new Date;
                            s.mangotouch.lazy && clearTimeout(s.mangotouch.lazy);
                            80 < u - s.mangotouch.tm || s.mangotouch.dry != f || s.mangotouch.drx != e ? (s.scrollmom.stop(), s.scrollmom.reset(t, n), s.mangotouch.sy = n, s.mangotouch.ly = n, s.mangotouch.sx = t, s.mangotouch.lx = t, s.mangotouch.dry = f, s.mangotouch.drx = e, s.mangotouch.tm = u) : (s.scrollmom.stop(), s.scrollmom.update(s.mangotouch.sx - r, s.mangotouch.sy - i), s.mangotouch.tm = u, i = Math.max(Math.abs(s.mangotouch.ly - n), Math.abs(s.mangotouch.lx - t)), s.mangotouch.ly = n, s.mangotouch.lx = t, 2 < i && (s.mangotouch.lazy = setTimeout(function () {
                                s.mangotouch.lazy = !1;
                                s.mangotouch.dry = 0;
                                s.mangotouch.drx = 0;
                                s.mangotouch.tm = 0;
                                s.scrollmom.doMomentum(30)
                            }, 100)))
                        }
                    }, t = s.getScrollTop(), i = s.getScrollLeft(), s.mangotouch = {
                        sy: t,
                        ly: t,
                        dry: 0,
                        sx: i,
                        lx: i,
                        drx: 0,
                        lazy: !1,
                        tm: 0
                    }, s.bind(s.docscroll, "scroll", s.onmangotouch)) : ((c.cantouch || s.istouchcapable || s.opt.touchbehavior || c.hasmstouch) && (s.scrollmom = new v(s), s.ontouchstart = function (t) {
                        var i, r;
                        if (t.pointerType && 2 != t.pointerType)return !1;
                        if (!s.locked) {
                            if (c.hasmstouch)for (i = t.target ? t.target : !1; i;) {
                                if (r = n(i).getNiceScroll(), 0 < r.length && r[0].me == s.me)break;
                                if (0 < r.length)return !1;
                                if ("DIV" == i.nodeName && i.id == s.id)break;
                                i = i.parentNode ? i.parentNode : !1
                            }
                            if (s.cancelScroll(), (i = s.getTarget(t)) && /INPUT/i.test(i.nodeName) && /range/i.test(i.type))return s.stopPropagation(t);
                            if (!("clientX"in t) && "changedTouches"in t && (t.clientX = t.changedTouches[0].clientX, t.clientY = t.changedTouches[0].clientY), s.forcescreen && (r = t, t = {original: t.original ? t.original : t}, t.clientX = r.screenX, t.clientY = r.screenY), s.rail.drag = {
                                    x: t.clientX,
                                    y: t.clientY,
                                    sx: s.scroll.x,
                                    sy: s.scroll.y,
                                    st: s.getScrollTop(),
                                    sl: s.getScrollLeft(),
                                    pt: 2,
                                    dl: !1
                                }, s.ispage || !s.opt.directionlockdeadzone)s.rail.drag.dl = "f"; else {
                                var r = n(window).width(), u = n(window).height(), f = Math.max(document.body.scrollWidth, document.documentElement.scrollWidth), e = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight), u = Math.max(0, e - u), r = Math.max(0, f - r);
                                s.rail.drag.ck = !s.rail.scrollable && s.railh.scrollable ? 0 < u ? "v" : !1 : s.rail.scrollable && !s.railh.scrollable ? 0 < r ? "h" : !1 : !1;
                                s.rail.drag.ck || (s.rail.drag.dl = "f")
                            }
                            if (s.opt.touchbehavior && s.isiframe && c.isie && (r = s.win.position(), s.rail.drag.x += r.left, s.rail.drag.y += r.top), s.hasmoving = !1, s.lastmouseup = !1, s.scrollmom.reset(t.clientX, t.clientY), !c.cantouch && !this.istouchcapable && !c.hasmstouch) {
                                if (!i || !/INPUT|SELECT|TEXTAREA/i.test(i.nodeName))return !s.ispage && c.hasmousecapture && i.setCapture(), s.cancelEvent(t);
                                /SUBMIT|CANCEL|BUTTON/i.test(n(i).attr("type")) && (pc = {
                                    tg: i,
                                    click: !1
                                }, s.preventclick = pc)
                            }
                        }
                    }, s.ontouchend = function (n) {
                        return n.pointerType && 2 != n.pointerType ? !1 : s.rail.drag && 2 == s.rail.drag.pt && (s.scrollmom.doMomentum(), s.rail.drag = !1, s.hasmoving && (s.hasmoving = !1, s.lastmouseup = !0, s.hideCursor(), c.hasmousecapture && document.releaseCapture(), !c.cantouch)) ? s.cancelEvent(n) : void 0
                    }, g = s.opt.touchbehavior && s.isiframe && !c.hasmousecapture, s.ontouchmove = function (t, i) {
                        var e, f, u;
                        if (t.pointerType && 2 != t.pointerType)return !1;
                        if (s.rail.drag && 2 == s.rail.drag.pt) {
                            if (c.cantouch && "undefined" == typeof t.original)return !0;
                            s.hasmoving = !0;
                            s.preventclick && !s.preventclick.click && (s.preventclick.click = s.preventclick.tg.onclick || !1, s.preventclick.tg.onclick = s.onpreventclick);
                            t = n.extend({original: t}, t);
                            "changedTouches"in t && (t.clientX = t.changedTouches[0].clientX, t.clientY = t.changedTouches[0].clientY);
                            s.forcescreen && (f = t, t = {original: t.original ? t.original : t}, t.clientX = f.screenX, t.clientY = f.screenY);
                            f = ofy = 0;
                            g && !i && (e = s.win.position(), f = -e.left, ofy = -e.top);
                            var h = t.clientY + ofy, e = h - s.rail.drag.y, l = t.clientX + f, o = l - s.rail.drag.x, r = s.rail.drag.st - e;
                            if (s.ishwscroll && s.opt.bouncescroll ? 0 > r ? r = Math.round(r / 2) : r > s.page.maxh && (r = s.page.maxh + Math.round((r - s.page.maxh) / 2)) : (0 > r && (h = r = 0), r > s.page.maxh && (r = s.page.maxh, h = 0)), s.railh && s.railh.scrollable && (u = s.rail.drag.sl - o, s.ishwscroll && s.opt.bouncescroll ? 0 > u ? u = Math.round(u / 2) : u > s.page.maxw && (u = s.page.maxw + Math.round((u - s.page.maxw) / 2)) : (0 > u && (l = u = 0), u > s.page.maxw && (u = s.page.maxw, l = 0))), f = !1, s.rail.drag.dl)f = !0, "v" == s.rail.drag.dl ? u = s.rail.drag.sl : "h" == s.rail.drag.dl && (r = s.rail.drag.st); else {
                                var e = Math.abs(e), o = Math.abs(o), a = s.opt.directionlockdeadzone;
                                if ("v" == s.rail.drag.ck) {
                                    if (e > a && o <= .3 * e)return s.rail.drag = !1, !0;
                                    o > a && (s.rail.drag.dl = "f", n("body").scrollTop(n("body").scrollTop()))
                                } else if ("h" == s.rail.drag.ck) {
                                    if (o > a && e <= .3 * az)return s.rail.drag = !1, !0;
                                    e > a && (s.rail.drag.dl = "f", n("body").scrollLeft(n("body").scrollLeft()))
                                }
                            }
                            if (s.synched("touchmove", function () {
                                    s.rail.drag && 2 == s.rail.drag.pt && (s.prepareTransition && s.prepareTransition(0), s.rail.scrollable && s.setScrollTop(r), s.scrollmom.update(l, h), s.railh && s.railh.scrollable ? (s.setScrollLeft(u), s.showCursor(r, u)) : s.showCursor(r), c.isie10 && document.selection.clear())
                                }), c.ischrome && s.istouchcapable && (f = !1), f)return s.cancelEvent(t)
                        }
                    }), s.onmousedown = function (n, t) {
                        if (!(s.rail.drag && 1 != s.rail.drag.pt)) {
                            if (s.locked)return s.cancelEvent(n);
                            s.cancelScroll();
                            s.rail.drag = {x: n.clientX, y: n.clientY, sx: s.scroll.x, sy: s.scroll.y, pt: 1, hr: !!t};
                            var i = s.getTarget(n);
                            return !s.ispage && c.hasmousecapture && i.setCapture(), s.isiframe && !c.hasmousecapture && (s.saved.csspointerevents = s.doc.css("pointer-events"), s.css(s.doc, {"pointer-events": "none"})), s.cancelEvent(n)
                        }
                    }, s.onmouseup = function (n) {
                        if (s.rail.drag && (c.hasmousecapture && document.releaseCapture(), s.isiframe && !c.hasmousecapture && s.doc.css("pointer-events", s.saved.csspointerevents), 1 == s.rail.drag.pt))return s.rail.drag = !1, s.cancelEvent(n)
                    }, s.onmousemove = function (n) {
                        if (s.rail.drag && 1 == s.rail.drag.pt) {
                            if (c.ischrome && 0 == n.which)return s.onmouseup(n);
                            if (s.cursorfreezed = !0, s.rail.drag.hr) {
                                s.scroll.x = s.rail.drag.sx + (n.clientX - s.rail.drag.x);
                                0 > s.scroll.x && (s.scroll.x = 0);
                                var t = s.scrollvaluemaxw;
                                s.scroll.x > t && (s.scroll.x = t)
                            } else s.scroll.y = s.rail.drag.sy + (n.clientY - s.rail.drag.y), 0 > s.scroll.y && (s.scroll.y = 0), t = s.scrollvaluemax, s.scroll.y > t && (s.scroll.y = t);
                            return s.synched("mousemove", function () {
                                s.rail.drag && 1 == s.rail.drag.pt && (s.showCursor(), s.rail.drag.hr ? s.doScrollLeft(Math.round(s.scroll.x * s.scrollratio.x), s.opt.cursordragspeed) : s.doScrollTop(Math.round(s.scroll.y * s.scrollratio.y), s.opt.cursordragspeed))
                            }), s.cancelEvent(n)
                        }
                    }, c.cantouch || s.opt.touchbehavior ? (s.onpreventclick = function (n) {
                        if (s.preventclick)return s.preventclick.tg.onclick = s.preventclick.click, s.preventclick = !1, s.cancelEvent(n)
                    }, s.bind(s.win, "mousedown", s.ontouchstart), s.onclick = c.isios ? !1 : function (n) {
                        return s.lastmouseup ? (s.lastmouseup = !1, s.cancelEvent(n)) : !0
                    }, s.opt.grabcursorenabled && c.cursorgrabvalue && (s.css(s.ispage ? s.doc : s.win, {cursor: c.cursorgrabvalue}), s.css(s.rail, {cursor: c.cursorgrabvalue}))) : (p = function (n) {
                        if (s.selectiondrag) {
                            if (n) {
                                var t = s.win.outerHeight();
                                n = n.pageY - s.selectiondrag.top;
                                0 < n && n < t && (n = 0);
                                n >= t && (n -= t);
                                s.selectiondrag.df = n
                            }
                            0 != s.selectiondrag.df && (s.doScrollBy(2 * -Math.floor(s.selectiondrag.df / 6)), s.debounced("doselectionscroll", function () {
                                p()
                            }, 50))
                        }
                    }, s.hasTextSelected = "getSelection"in document ? function () {
                        return 0 < document.getSelection().rangeCount
                    } : "selection"in document ? function () {
                        return "None" != document.selection.type
                    } : function () {
                        return !1
                    }, s.onselectionstart = function () {
                        s.ispage || (s.selectiondrag = s.win.offset())
                    }, s.onselectionend = function () {
                        s.selectiondrag = !1
                    }, s.onselectiondrag = function (n) {
                        s.selectiondrag && s.hasTextSelected() && s.debounced("selectionscroll", function () {
                            p(n)
                        }, 250)
                    }), c.hasmstouch && (s.css(s.rail, {"-ms-touch-action": "none"}), s.css(s.cursor, {"-ms-touch-action": "none"}), s.bind(s.win, "MSPointerDown", s.ontouchstart), s.bind(document, "MSPointerUp", s.ontouchend), s.bind(document, "MSPointerMove", s.ontouchmove), s.bind(s.cursor, "MSGestureHold", function (n) {
                        n.preventDefault()
                    }), s.bind(s.cursor, "contextmenu", function (n) {
                        n.preventDefault()
                    })), this.istouchcapable && (s.bind(s.win, "touchstart", s.ontouchstart), s.bind(document, "touchend", s.ontouchend), s.bind(document, "touchcancel", s.ontouchend), s.bind(document, "touchmove", s.ontouchmove)), s.bind(s.cursor, "mousedown", s.onmousedown), s.bind(s.cursor, "mouseup", s.onmouseup), s.railh && (s.bind(s.cursorh, "mousedown", function (n) {
                        s.onmousedown(n, !0)
                    }), s.bind(s.cursorh, "mouseup", function (n) {
                        if (!s.rail.drag || 2 != s.rail.drag.pt)return s.rail.drag = !1, s.hasmoving = !1, s.hideCursor(), c.hasmousecapture && document.releaseCapture(), s.cancelEvent(n)
                    })), !s.opt.cursordragontouch && (c.cantouch || s.opt.touchbehavior) || (s.rail.css({cursor: "default"}), s.railh && s.railh.css({cursor: "default"}), s.jqbind(s.rail, "mouseenter", function () {
                        s.canshowonmouseevent && s.showCursor();
                        s.rail.active = !0
                    }), s.jqbind(s.rail, "mouseleave", function () {
                        s.rail.active = !1;
                        s.rail.drag || s.hideCursor()
                    }), s.opt.sensitiverail && (s.bind(s.rail, "click", function (n) {
                        s.doRailClick(n, !1, !1)
                    }), s.bind(s.rail, "dblclick", function (n) {
                        s.doRailClick(n, !0, !1)
                    }), s.bind(s.cursor, "click", function (n) {
                        s.cancelEvent(n)
                    }), s.bind(s.cursor, "dblclick", function (n) {
                        s.cancelEvent(n)
                    })), s.railh && (s.jqbind(s.railh, "mouseenter", function () {
                        s.canshowonmouseevent && s.showCursor();
                        s.rail.active = !0
                    }), s.jqbind(s.railh, "mouseleave", function () {
                        s.rail.active = !1;
                        s.rail.drag || s.hideCursor()
                    }), s.opt.sensitiverail && (s.bind(s.railh, "click", function (n) {
                        s.doRailClick(n, !1, !0)
                    }), s.bind(s.railh, "dblclick", function (n) {
                        s.doRailClick(n, !0, !0)
                    }), s.bind(s.cursorh, "click", function (n) {
                        s.cancelEvent(n)
                    }), s.bind(s.cursorh, "dblclick", function (n) {
                        s.cancelEvent(n)
                    })))), !c.cantouch && !s.opt.touchbehavior ? (s.bind(c.hasmousecapture ? s.win : document, "mouseup", s.onmouseup), s.bind(document, "mousemove", s.onmousemove), s.onclick && s.bind(document, "click", s.onclick), !s.ispage && s.opt.enablescrollonselection && (s.bind(s.win[0], "mousedown", s.onselectionstart), s.bind(document, "mouseup", s.onselectionend), s.bind(s.cursor, "mouseup", s.onselectionend), s.cursorh && s.bind(s.cursorh, "mouseup", s.onselectionend), s.bind(document, "mousemove", s.onselectiondrag)), s.zoom && (s.jqbind(s.zoom, "mouseenter", function () {
                        s.canshowonmouseevent && s.showCursor();
                        s.rail.active = !0
                    }), s.jqbind(s.zoom, "mouseleave", function () {
                        s.rail.active = !1;
                        s.rail.drag || s.hideCursor()
                    }))) : (s.bind(c.hasmousecapture ? s.win : document, "mouseup", s.ontouchend), s.bind(document, "mousemove", s.ontouchmove), s.onclick && s.bind(document, "click", s.onclick), s.opt.cursordragontouch && (s.bind(s.cursor, "mousedown", s.onmousedown), s.bind(s.cursor, "mousemove", s.onmousemove), s.cursorh && s.bind(s.cursorh, "mousedown", s.onmousedown), s.cursorh && s.bind(s.cursorh, "mousemove", s.onmousemove))), s.opt.enablemousewheel && (s.isiframe || s.bind(c.isie && s.ispage ? document : s.docscroll, "mousewheel", s.onmousewheel), s.bind(s.rail, "mousewheel", s.onmousewheel), s.railh && s.bind(s.railh, "mousewheel", s.onmousewheelhr)), s.ispage || c.cantouch || /HTML|BODY/.test(s.win[0].nodeName) || (s.win.attr("tabindex") || s.win.attr({tabindex: y++}), s.jqbind(s.win, "focus", function (n) {
                        e = s.getTarget(n).id || !0;
                        s.hasfocus = !0;
                        s.canshowonmouseevent && s.noticeCursor()
                    }), s.jqbind(s.win, "blur", function () {
                        e = !1;
                        s.hasfocus = !1
                    }), s.jqbind(s.win, "mouseenter", function (n) {
                        h = s.getTarget(n).id || !0;
                        s.hasmousefocus = !0;
                        s.canshowonmouseevent && s.noticeCursor()
                    }), s.jqbind(s.win, "mouseleave", function () {
                        h = !1;
                        s.hasmousefocus = !1
                    })));
                    s.onkeypress = function (n) {
                        var i;
                        if (s.locked && 0 == s.page.maxh || (n = n ? n : window.e, i = s.getTarget(n), i && /INPUT|TEXTAREA|SELECT|OPTION/.test(i.nodeName) && (!i.getAttribute("type") && !i.type || !/submit|button|cancel/i.tp)))return !0;
                        if (s.hasfocus || s.hasmousefocus && !e || s.ispage && !e && !h) {
                            if (i = n.keyCode, s.locked && 27 != i)return s.cancelEvent(n);
                            var r = n.ctrlKey || !1, u = n.shiftKey || !1, t = !1;
                            switch (i) {
                                case 38:
                                case 63233:
                                    s.doScrollBy(72);
                                    t = !0;
                                    break;
                                case 40:
                                case 63235:
                                    s.doScrollBy(-72);
                                    t = !0;
                                    break;
                                case 37:
                                case 63232:
                                    s.railh && (r ? s.doScrollLeft(0) : s.doScrollLeftBy(72), t = !0);
                                    break;
                                case 39:
                                case 63234:
                                    s.railh && (r ? s.doScrollLeft(s.page.maxw) : s.doScrollLeftBy(-72), t = !0);
                                    break;
                                case 33:
                                case 63276:
                                    s.doScrollBy(s.view.h);
                                    t = !0;
                                    break;
                                case 34:
                                case 63277:
                                    s.doScrollBy(-s.view.h);
                                    t = !0;
                                    break;
                                case 36:
                                case 63273:
                                    s.railh && r ? s.doScrollPos(0, 0) : s.doScrollTo(0);
                                    t = !0;
                                    break;
                                case 35:
                                case 63275:
                                    s.railh && r ? s.doScrollPos(s.page.maxw, s.page.maxh) : s.doScrollTo(s.page.maxh);
                                    t = !0;
                                    break;
                                case 32:
                                    s.opt.spacebarenabled && (u ? s.doScrollBy(s.view.h) : s.doScrollBy(-s.view.h), t = !0);
                                    break;
                                case 27:
                                    s.zoomactive && (s.doZoom(), t = !0)
                            }
                            if (t)return s.cancelEvent(n)
                        }
                    };
                    s.opt.enablekeyboard && s.bind(document, c.isopera && !c.isopera12 ? "keypress" : "keydown", s.onkeypress);
                    s.bind(window, "resize", s.lazyResize);
                    s.bind(window, "orientationchange", s.lazyResize);
                    s.bind(window, "load", s.lazyResize);
                    !c.ischrome || s.ispage || s.haswrapper || (nt = s.win.attr("style"), t = parseFloat(s.win.css("width")) + 1, s.win.css("width", t), s.synched("chromefix", function () {
                        s.win.attr("style", nt)
                    }));
                    s.onAttributeChange = function () {
                        s.lazyResize(250)
                    };
                    s.ispage || s.haswrapper || (!1 !== o ? (s.observer = new o(function (n) {
                        n.forEach(s.onAttributeChange)
                    }), s.observer.observe(s.win[0], {
                        childList: !0,
                        characterData: !1,
                        attributes: !0,
                        subtree: !1
                    }), s.observerremover = new o(function (n) {
                        n.forEach(function (n) {
                            if (0 < n.removedNodes.length)for (var t in n.removedNodes)if (n.removedNodes[t] == s.win[0])return s.remove()
                        })
                    }), s.observerremover.observe(s.win[0].parentNode, {
                        childList: !0,
                        characterData: !1,
                        attributes: !1,
                        subtree: !1
                    })) : (s.bind(s.win, c.isie && !c.isie9 ? "propertychange" : "DOMAttrModified", s.onAttributeChange), c.isie9 && s.win[0].attachEvent("onpropertychange", s.onAttributeChange), s.bind(s.win, "DOMNodeRemoved", function (n) {
                        n.target == s.win[0] && s.remove()
                    })));
                    !s.ispage && s.opt.boxzoom && s.bind(window, "resize", s.resizeZoom);
                    s.istextarea && s.bind(s.win, "mouseup", s.lazyResize);
                    s.checkrtlmode = !0;
                    s.lazyResize(30)
                }
                "IFRAME" == this.doc[0].nodeName && (b = function (t) {
                    s.iframexd = !1;
                    try {
                        var i = "contentDocument"in this ? this.contentDocument : this.contentWindow.document
                    } catch (r) {
                        s.iframexd = !0;
                        i = !1
                    }
                    if (s.iframexd)return "console"in window && console.log("NiceScroll error: policy restriced iframe"), !0;
                    s.forcescreen = !0;
                    s.isiframe && (s.iframe = {
                        doc: n(i),
                        html: s.doc.contents().find("html")[0],
                        body: s.doc.contents().find("body")[0]
                    }, s.getContentSize = function () {
                        return {
                            w: Math.max(s.iframe.html.scrollWidth, s.iframe.body.scrollWidth),
                            h: Math.max(s.iframe.html.scrollHeight, s.iframe.body.scrollHeight)
                        }
                    }, s.docscroll = n(s.iframe.body));
                    c.isios || !s.opt.iframeautoresize || s.isiframe || (s.win.scrollTop(0), s.doc.height(""), t = Math.max(i.getElementsByTagName("html")[0].scrollHeight, i.body.scrollHeight), s.doc.height(t));
                    s.lazyResize(30);
                    c.isie7 && s.css(n(s.iframe.html), {"overflow-y": "hidden"});
                    s.css(n(s.iframe.body), {"overflow-y": "hidden"});
                    "contentWindow"in this ? s.bind(this.contentWindow, "scroll", s.onscroll) : s.bind(i, "scroll", s.onscroll);
                    s.opt.enablemousewheel && s.bind(i, "mousewheel", s.onmousewheel);
                    s.opt.enablekeyboard && s.bind(i, c.isopera ? "keypress" : "keydown", s.onkeypress);
                    (c.cantouch || s.opt.touchbehavior) && (s.bind(i, "mousedown", s.onmousedown), s.bind(i, "mousemove", function (n) {
                        s.onmousemove(n, !0)
                    }), s.opt.grabcursorenabled && c.cursorgrabvalue && s.css(n(i.body), {cursor: c.cursorgrabvalue}));
                    s.bind(i, "mouseup", s.onmouseup);
                    s.zoom && (s.opt.dblclickzoom && s.bind(i, "dblclick", s.doZoom), s.ongesturezoom && s.bind(i, "gestureend", s.ongesturezoom))
                }, this.doc[0].readyState && "complete" == this.doc[0].readyState && setTimeout(function () {
                    b.call(s.doc[0], !1)
                }, 500), s.bind(this.doc, "load", b))
            };
            this.showCursor = function (n, t) {
                s.cursortimeout && (clearTimeout(s.cursortimeout), s.cursortimeout = 0);
                s.rail && (s.autohidedom && (s.autohidedom.stop().css({opacity: s.opt.cursoropacitymax}), s.cursoractive = !0), s.rail.drag && 1 == s.rail.drag.pt || ("undefined" != typeof n && !1 !== n && (s.scroll.y = Math.round(1 * n / s.scrollratio.y)), "undefined" != typeof t && (s.scroll.x = Math.round(1 * t / s.scrollratio.x))), s.cursor.css({
                    height: s.cursorheight,
                    top: s.scroll.y
                }), s.cursorh && (!s.rail.align && s.rail.visibility ? s.cursorh.css({
                    width: s.cursorwidth,
                    left: s.scroll.x + s.rail.width
                }) : s.cursorh.css({
                    width: s.cursorwidth,
                    left: s.scroll.x
                }), s.cursoractive = !0), s.zoom && s.zoom.stop().css({opacity: s.opt.cursoropacitymax}))
            };
            this.hideCursor = function (n) {
                !s.cursortimeout && s.rail && s.autohidedom && (s.cursortimeout = setTimeout(function () {
                    s.rail.active && s.showonmouseevent || (s.autohidedom.stop().animate({opacity: s.opt.cursoropacitymin}), s.zoom && s.zoom.stop().animate({opacity: s.opt.cursoropacitymin}), s.cursoractive = !1);
                    s.cursortimeout = 0
                }, n || s.opt.hidecursordelay))
            };
            this.noticeCursor = function (n, t, i) {
                s.showCursor(t, i);
                s.rail.active || s.hideCursor(n)
            };
            this.getContentSize = s.ispage ? function () {
                return {
                    w: Math.max(document.body.scrollWidth, document.documentElement.scrollWidth),
                    h: Math.max(document.body.scrollHeight, document.documentElement.scrollHeight)
                }
            } : s.haswrapper ? function () {
                return {
                    w: s.doc.outerWidth() + parseInt(s.win.css("paddingLeft")) + parseInt(s.win.css("paddingRight")),
                    h: s.doc.outerHeight() + parseInt(s.win.css("paddingTop")) + parseInt(s.win.css("paddingBottom"))
                }
            } : function () {
                return {w: s.docscroll[0].scrollWidth, h: s.docscroll[0].scrollHeight}
            };
            this.onResize = function (n, t) {
                if (!s.win)return !1;
                if (!s.haswrapper && !s.ispage) {
                    if ("none" == s.win.css("display"))return s.visibility && s.hideRail().hideRailHr(), !1;
                    s.hidden || s.visibility || s.showRail().showRailHr()
                }
                var i = s.page.maxh, r = s.page.maxw, u = s.view.w;
                if (s.view = {
                        w: s.ispage ? s.win.width() : parseInt(s.win[0].clientWidth),
                        h: s.ispage ? s.win.height() : parseInt(s.win[0].clientHeight)
                    }, s.page = t ? t : s.getContentSize(), s.page.maxh = Math.max(0, s.page.h - s.view.h), s.page.maxw = Math.max(0, s.page.w - s.view.w), s.page.maxh == i && s.page.maxw == r && s.view.w == u) {
                    if (s.ispage || (i = s.win.offset(), s.lastposition && (r = s.lastposition, r.top == i.top && r.left == i.left)))return s;
                    s.lastposition = i
                }
                return (0 == s.page.maxh ? (s.hideRail(), s.scrollvaluemax = 0, s.scroll.y = 0, s.scrollratio.y = 0, s.cursorheight = 0, s.setScrollTop(0), s.rail.scrollable = !1) : s.rail.scrollable = !0, 0 == s.page.maxw ? (s.hideRailHr(), s.scrollvaluemaxw = 0, s.scroll.x = 0, s.scrollratio.x = 0, s.cursorwidth = 0, s.setScrollLeft(0), s.railh.scrollable = !1) : s.railh.scrollable = !0, s.locked = 0 == s.page.maxh && 0 == s.page.maxw, s.locked) ? (s.ispage || s.updateScrollBar(s.view), !1) : (!s.hidden && !s.visibility ? s.showRail().showRailHr() : !s.hidden && !s.railh.visibility && s.showRailHr(), s.istextarea && s.win.css("resize") && "none" != s.win.css("resize") && (s.view.h -= 20), s.cursorheight = Math.min(s.view.h, Math.round(s.view.h * (s.view.h / s.page.h))), s.cursorheight = s.opt.cursorfixedheight ? s.opt.cursorfixedheight : Math.max(s.opt.cursorminheight, s.cursorheight), s.cursorwidth = Math.min(s.view.w, Math.round(s.view.w * (s.view.w / s.page.w))), s.cursorwidth = s.opt.cursorfixedheight ? s.opt.cursorfixedheight : Math.max(s.opt.cursorminheight, s.cursorwidth), s.scrollvaluemax = s.view.h - s.cursorheight - s.cursor.hborder, s.railh && (s.railh.width = 0 < s.page.maxh ? s.view.w - s.rail.width : s.view.w, s.scrollvaluemaxw = s.railh.width - s.cursorwidth - s.cursorh.wborder), s.checkrtlmode && s.railh && (s.checkrtlmode = !1, s.opt.rtlmode && 0 == s.scroll.x && s.setScrollLeft(s.page.maxw)), s.ispage || s.updateScrollBar(s.view), s.scrollratio = {
                    x: s.page.maxw / s.scrollvaluemaxw,
                    y: s.page.maxh / s.scrollvaluemax
                }, s.getScrollTop() > s.page.maxh ? s.doScrollTop(s.page.maxh) : (s.scroll.y = Math.round(s.getScrollTop() * (1 / s.scrollratio.y)), s.scroll.x = Math.round(s.getScrollLeft() * (1 / s.scrollratio.x)), s.cursoractive && s.noticeCursor()), s.scroll.y && 0 == s.getScrollTop() && s.doScrollTo(Math.floor(s.scroll.y * s.scrollratio.y)), s)
            };
            this.resize = s.onResize;
            this.lazyResize = function (n) {
                return n = isNaN(n) ? 30 : n, s.delayed("resize", s.resize, n), s
            };
            this._bind = function (n, t, i, r) {
                s.events.push({e: n, n: t, f: i, b: r, q: !1});
                n.addEventListener ? n.addEventListener(t, i, r || !1) : n.attachEvent ? n.attachEvent("on" + t, i) : n["on" + t] = i
            };
            this.jqbind = function (t, i, r) {
                s.events.push({e: t, n: i, f: r, q: !0});
                n(t).bind(i, r)
            };
            this.bind = function (n, t, i, r) {
                var u = "jquery"in n ? n[0] : n;
                "mousewheel" == t ? "onwheel"in s.win ? s._bind(u, "wheel", i, r || !1) : (n = "undefined" != typeof document.onmousewheel ? "mousewheel" : "DOMMouseScroll", g(u, n, i, r || !1), "DOMMouseScroll" == n && g(u, "MozMousePixelScroll", i, r || !1)) : u.addEventListener ? (c.cantouch && /mouseup|mousedown|mousemove/.test(t) && s._bind(u, "mousedown" == t ? "touchstart" : "mouseup" == t ? "touchend" : "touchmove", function (n) {
                    if (n.touches) {
                        if (2 > n.touches.length) {
                            var t = n.touches.length ? n.touches[0] : n;
                            t.original = n;
                            i.call(this, t)
                        }
                    } else n.changedTouches && (t = n.changedTouches[0], t.original = n, i.call(this, t))
                }, r || !1), s._bind(u, t, i, r || !1), c.cantouch && "mouseup" == t && s._bind(u, "touchcancel", i, r || !1)) : s._bind(u, t, function (n) {
                    return (n = n || window.event || !1) && n.srcElement && (n.target = n.srcElement), "pageY"in n || (n.pageX = n.clientX + document.documentElement.scrollLeft, n.pageY = n.clientY + document.documentElement.scrollTop), !1 === i.call(u, n) || !1 === r ? s.cancelEvent(n) : !0
                })
            };
            this._unbind = function (n, t, i, r) {
                n.removeEventListener ? n.removeEventListener(t, i, r) : n.detachEvent ? n.detachEvent("on" + t, i) : n["on" + t] = !1
            };
            this.unbindAll = function () {
                for (var n, t = 0; t < s.events.length; t++)n = s.events[t], n.q ? n.e.unbind(n.n, n.f) : s._unbind(n.e, n.n, n.f, n.b)
            };
            this.cancelEvent = function (n) {
                return (n = n.original ? n.original : n ? n : window.event || !1, !n) ? !1 : (n.preventDefault && n.preventDefault(), n.stopPropagation && n.stopPropagation(), n.preventManipulation && n.preventManipulation(), n.cancelBubble = !0, n.cancel = !0, n.returnValue = !1)
            };
            this.stopPropagation = function (n) {
                return (n = n.original ? n.original : n ? n : window.event || !1, !n) ? !1 : n.stopPropagation ? n.stopPropagation() : (n.cancelBubble && (n.cancelBubble = !0), !1)
            };
            this.showRail = function () {
                return 0 != s.page.maxh && (s.ispage || "none" != s.win.css("display")) && (s.visibility = !0, s.rail.visibility = !0, s.rail.css("display", "block")), s
            };
            this.showRailHr = function () {
                return s.railh ? (0 != s.page.maxw && (s.ispage || "none" != s.win.css("display")) && (s.railh.visibility = !0, s.railh.css("display", "block")), s) : s
            };
            this.hideRail = function () {
                return s.visibility = !1, s.rail.visibility = !1, s.rail.css("display", "none"), s
            };
            this.hideRailHr = function () {
                return s.railh ? (s.railh.visibility = !1, s.railh.css("display", "none"), s) : s
            };
            this.show = function () {
                return s.hidden = !1, s.locked = !1, s.showRail().showRailHr()
            };
            this.hide = function () {
                return s.hidden = !0, s.locked = !0, s.hideRail().hideRailHr()
            };
            this.toggle = function () {
                return s.hidden ? s.show() : s.hide()
            };
            this.remove = function () {
                var t, n;
                for (s.stop(), s.cursortimeout && clearTimeout(s.cursortimeout), s.doZoomOut(), s.unbindAll(), !1 !== s.observer && s.observer.disconnect(), !1 !== s.observerremover && s.observerremover.disconnect(), s.events = [], s.cursor && (s.cursor.remove(), s.cursor = null), s.cursorh && (s.cursorh.remove(), s.cursorh = null), s.rail && (s.rail.remove(), s.rail = null), s.railh && (s.railh.remove(), s.railh = null), s.zoom && (s.zoom.remove(), s.zoom = null), t = 0; t < s.saved.css.length; t++)n = s.saved.css[t], n[0].css(n[1], "undefined" == typeof n[2] ? "" : n[2]);
                return s.saved = !1, s.me.data("__nicescroll", ""), s.me = null, s.doc = null, s.docscroll = null, s.win = null, s
            };
            this.scrollstart = function (n) {
                return this.onscrollstart = n, s
            };
            this.scrollend = function (n) {
                return this.onscrollend = n, s
            };
            this.scrollcancel = function (n) {
                return this.onscrollcancel = n, s
            };
            this.zoomin = function (n) {
                return this.onzoomin = n, s
            };
            this.zoomout = function (n) {
                return this.onzoomout = n, s
            };
            this.isScrollable = function (t) {
                if (t = t.target ? t.target : t, "OPTION" == t.nodeName)return !0;
                for (; t && 1 == t.nodeType && !/BODY|HTML/.test(t.nodeName);) {
                    var i = n(t), i = i.css("overflowY") || i.css("overflowX") || i.css("overflow") || "";
                    if (/scroll|auto/.test(i))return t.clientHeight != t.scrollHeight;
                    t = t.parentNode ? t.parentNode : !1
                }
                return !1
            };
            this.getViewport = function (t) {
                for (t = t && t.parentNode ? t.parentNode : !1; t && 1 == t.nodeType && !/BODY|HTML/.test(t.nodeName);) {
                    var i = n(t), r = i.css("overflowY") || i.css("overflowX") || i.css("overflow") || "";
                    if (/scroll|auto/.test(r) && t.clientHeight != t.scrollHeight || 0 < i.getNiceScroll().length)return i;
                    t = t.parentNode ? t.parentNode : !1
                }
                return !1
            };
            this.onmousewheel = function (n) {
                if (s.locked)return !0;
                if (s.rail.drag)return s.cancelEvent(n);
                if (!s.rail.scrollable)return s.railh && s.railh.scrollable ? s.onmousewheelhr(n) : !0;
                var t = +new Date, i = !1;
                return (s.opt.preservenativescrolling && s.checkarea + 600 < t && (s.nativescrollingarea = s.isScrollable(n), i = !0), s.checkarea = t, s.nativescrollingarea) ? !0 : ((n = nt(n, !1, i)) && (s.checkarea = 0), n)
            };
            this.onmousewheelhr = function (n) {
                if (s.locked || !s.railh.scrollable)return !0;
                if (s.rail.drag)return s.cancelEvent(n);
                var t = +new Date, i = !1;
                return s.opt.preservenativescrolling && s.checkarea + 600 < t && (s.nativescrollingarea = s.isScrollable(n), i = !0), s.checkarea = t, s.nativescrollingarea ? !0 : s.locked ? s.cancelEvent(n) : nt(n, !0, i)
            };
            this.stop = function () {
                return s.cancelScroll(), s.scrollmon && s.scrollmon.stop(), s.cursorfreezed = !1, s.scroll.y = Math.round(s.getScrollTop() * (1 / s.scrollratio.y)), s.noticeCursor(), s
            };
            this.getTransitionSpeed = function (n) {
                var t = Math.round(10 * s.opt.scrollspeed);
                return n = Math.min(t, Math.round(n / 20 * s.opt.scrollspeed)), 20 < n ? n : 0
            };
            s.opt.smoothscroll ? s.ishwscroll && c.hastransition && s.opt.usetransition ? (this.prepareTransition = function (n, t) {
                var i = t ? 20 < n ? n : 0 : s.getTransitionSpeed(n), r = i ? c.prefixstyle + "transform " + i + "ms ease-out" : "";
                return s.lasttransitionstyle && s.lasttransitionstyle == r || (s.lasttransitionstyle = r, s.doc.css(c.transitionstyle, r)), i
            }, this.doScrollLeft = function (n, t) {
                var i = s.scrollrunning ? s.newscrolly : s.getScrollTop();
                s.doScrollPos(n, i, t)
            }, this.doScrollTop = function (n, t) {
                var i = s.scrollrunning ? s.newscrollx : s.getScrollLeft();
                s.doScrollPos(i, n, t)
            }, this.doScrollPos = function (n, t, i) {
                var r = s.getScrollTop(), u = s.getScrollLeft();
                if (((0 > (s.newscrolly - r) * (t - r) || 0 > (s.newscrollx - u) * (n - u)) && s.cancelScroll(), !1 == s.opt.bouncescroll && (0 > t ? t = 0 : t > s.page.maxh && (t = s.page.maxh), 0 > n ? n = 0 : n > s.page.maxw && (n = s.page.maxw)), s.scrollrunning && n == s.newscrollx && t == s.newscrolly) || (s.newscrolly = t, s.newscrollx = n, s.newscrollspeed = i || !1, s.timer))return !1;
                s.timer = setTimeout(function () {
                    var r = s.getScrollTop(), u = s.getScrollLeft(), i, f;
                    i = n - u;
                    f = t - r;
                    i = Math.round(Math.sqrt(Math.pow(i, 2) + Math.pow(f, 2)));
                    i = s.newscrollspeed && 1 < s.newscrollspeed ? s.newscrollspeed : s.getTransitionSpeed(i);
                    s.newscrollspeed && 1 >= s.newscrollspeed && (i *= s.newscrollspeed);
                    s.prepareTransition(i, !0);
                    s.timerscroll && s.timerscroll.tm && clearInterval(s.timerscroll.tm);
                    0 < i && (!s.scrollrunning && s.onscrollstart && s.onscrollstart.call(s, {
                        type: "scrollstart",
                        current: {x: u, y: r},
                        request: {x: n, y: t},
                        end: {x: s.newscrollx, y: s.newscrolly},
                        speed: i
                    }), c.transitionend ? s.scrollendtrapped || (s.scrollendtrapped = !0, s.bind(s.doc, c.transitionend, s.onScrollEnd, !1)) : (s.scrollendtrapped && clearTimeout(s.scrollendtrapped), s.scrollendtrapped = setTimeout(s.onScrollEnd, i)), s.timerscroll = {
                        bz: new BezierClass(r, s.newscrolly, i, 0, 0, .58, 1),
                        bh: new BezierClass(u, s.newscrollx, i, 0, 0, .58, 1)
                    }, s.cursorfreezed || (s.timerscroll.tm = setInterval(function () {
                        s.showCursor(s.getScrollTop(), s.getScrollLeft())
                    }, 60)));
                    s.synched("doScroll-set", function () {
                        s.timer = 0;
                        s.scrollendtrapped && (s.scrollrunning = !0);
                        s.setScrollTop(s.newscrolly);
                        s.setScrollLeft(s.newscrollx);
                        s.scrollendtrapped || s.onScrollEnd()
                    })
                }, 50)
            }, this.cancelScroll = function () {
                if (!s.scrollendtrapped)return !0;
                var n = s.getScrollTop(), t = s.getScrollLeft();
                return s.scrollrunning = !1, c.transitionend || clearTimeout(c.transitionend), s.scrollendtrapped = !1, s._unbind(s.doc, c.transitionend, s.onScrollEnd), s.prepareTransition(0), s.setScrollTop(n), s.railh && s.setScrollLeft(t), s.timerscroll && s.timerscroll.tm && clearInterval(s.timerscroll.tm), s.timerscroll = !1, s.cursorfreezed = !1, s.showCursor(n, t), s
            }, this.onScrollEnd = function () {
                s.scrollendtrapped && s._unbind(s.doc, c.transitionend, s.onScrollEnd);
                s.scrollendtrapped = !1;
                s.prepareTransition(0);
                s.timerscroll && s.timerscroll.tm && clearInterval(s.timerscroll.tm);
                s.timerscroll = !1;
                var n = s.getScrollTop(), t = s.getScrollLeft();
                if (s.setScrollTop(n), s.railh && s.setScrollLeft(t), s.noticeCursor(!1, n, t), s.cursorfreezed = !1, 0 > n ? n = 0 : n > s.page.maxh && (n = s.page.maxh), 0 > t ? t = 0 : t > s.page.maxw && (t = s.page.maxw), n != s.newscrolly || t != s.newscrollx)return s.doScrollPos(t, n, s.opt.snapbackspeed);
                s.onscrollend && s.scrollrunning && s.onscrollend.call(s, {
                    type: "scrollend",
                    current: {x: t, y: n},
                    end: {x: s.newscrollx, y: s.newscrolly}
                });
                s.scrollrunning = !1
            }) : (this.doScrollLeft = function (n, t) {
                var i = s.scrollrunning ? s.newscrolly : s.getScrollTop();
                s.doScrollPos(n, i, t)
            }, this.doScrollTop = function (n, t) {
                var i = s.scrollrunning ? s.newscrollx : s.getScrollLeft();
                s.doScrollPos(i, n, t)
            }, this.doScrollPos = function (n, r, u) {
                function l() {
                    var r, n, u, i;
                    if (s.cancelAnimationFrame)return !0;
                    if (s.scrollrunning = !0, a = 1 - a)return s.timer = t(l) || 1;
                    r = 0;
                    n = sy = s.getScrollTop();
                    s.dst.ay ? (n = s.bzscroll ? s.dst.py + s.bzscroll.getNow() * s.dst.ay : s.newscrolly, u = n - sy, (0 > u && n < s.newscrolly || 0 < u && n > s.newscrolly) && (n = s.newscrolly), s.setScrollTop(n), n == s.newscrolly && (r = 1)) : r = 1;
                    i = sx = s.getScrollLeft();
                    s.dst.ax ? (i = s.bzscroll ? s.dst.px + s.bzscroll.getNow() * s.dst.ax : s.newscrollx, u = i - sx, (0 > u && i < s.newscrollx || 0 < u && i > s.newscrollx) && (i = s.newscrollx), s.setScrollLeft(i), i == s.newscrollx && (r += 1)) : r += 1;
                    2 == r ? (s.timer = 0, s.cursorfreezed = !1, s.bzscroll = !1, s.scrollrunning = !1, 0 > n ? n = 0 : n > s.page.maxh && (n = s.page.maxh), 0 > i ? i = 0 : i > s.page.maxw && (i = s.page.maxw), i != s.newscrollx || n != s.newscrolly ? s.doScrollPos(i, n) : s.onscrollend && s.onscrollend.call(s, {
                        type: "scrollend",
                        current: {x: sx, y: sy},
                        end: {x: s.newscrollx, y: s.newscrolly}
                    })) : s.timer = t(l) || 1
                }

                var e, o, f, c, h, a;
                if (r = "undefined" == typeof r || !1 === r ? s.getScrollTop(!0) : r, s.timer && s.newscrolly == r && s.newscrollx == n)return !0;
                s.timer && i(s.timer);
                s.timer = 0;
                e = s.getScrollTop();
                o = s.getScrollLeft();
                (0 > (s.newscrolly - e) * (r - e) || 0 > (s.newscrollx - o) * (n - o)) && s.cancelScroll();
                s.newscrolly = r;
                s.newscrollx = n;
                s.bouncescroll && s.rail.visibility || (0 > s.newscrolly ? s.newscrolly = 0 : s.newscrolly > s.page.maxh && (s.newscrolly = s.page.maxh));
                s.bouncescroll && s.railh.visibility || (0 > s.newscrollx ? s.newscrollx = 0 : s.newscrollx > s.page.maxw && (s.newscrollx = s.page.maxw));
                s.dst = {};
                s.dst.x = n - o;
                s.dst.y = r - e;
                s.dst.px = o;
                s.dst.py = e;
                f = Math.round(Math.sqrt(Math.pow(s.dst.x, 2) + Math.pow(s.dst.y, 2)));
                s.dst.ax = s.dst.x / f;
                s.dst.ay = s.dst.y / f;
                c = 0;
                h = f;
                0 == s.dst.x ? (c = e, h = r, s.dst.ay = 1, s.dst.py = 0) : 0 == s.dst.y && (c = o, h = n, s.dst.ax = 1, s.dst.px = 0);
                f = s.getTransitionSpeed(f);
                u && 1 >= u && (f *= u);
                s.bzscroll = 0 < f ? s.bzscroll ? s.bzscroll.update(h, f) : new BezierClass(c, h, f, 0, 1, 0, 1) : !1;
                s.timer || ((e == s.page.maxh && r >= s.page.maxh || o == s.page.maxw && n >= s.page.maxw) && s.checkContentSize(), a = 1, s.cancelAnimationFrame = !1, s.timer = 1, s.onscrollstart && !s.scrollrunning && s.onscrollstart.call(s, {
                    type: "scrollstart",
                    current: {x: o, y: e},
                    request: {x: n, y: r},
                    end: {x: s.newscrollx, y: s.newscrolly},
                    speed: f
                }), l(), (e == s.page.maxh && r >= e || o == s.page.maxw && n >= o) && s.checkContentSize(), s.noticeCursor())
            }, this.cancelScroll = function () {
                return s.timer && i(s.timer), s.timer = 0, s.bzscroll = !1, s.scrollrunning = !1, s
            }) : (this.doScrollLeft = function (n, t) {
                var i = s.getScrollTop();
                s.doScrollPos(n, i, t)
            }, this.doScrollTop = function (n, t) {
                var i = s.getScrollLeft();
                s.doScrollPos(i, n, t)
            }, this.doScrollPos = function (n, t) {
                var r = n > s.page.maxw ? s.page.maxw : n, i;
                0 > r && (r = 0);
                i = t > s.page.maxh ? s.page.maxh : t;
                0 > i && (i = 0);
                s.synched("scroll", function () {
                    s.setScrollTop(i);
                    s.setScrollLeft(r)
                })
            }, this.cancelScroll = function () {
            });
            this.doScrollBy = function (n, t) {
                var i = 0, i = t ? Math.floor((s.scroll.y - n) * s.scrollratio.y) : (s.timer ? s.newscrolly : s.getScrollTop(!0)) - n, r;
                if (s.bouncescroll && (r = Math.round(s.view.h / 2), i < -r ? i = -r : i > s.page.maxh + r && (i = s.page.maxh + r)), s.cursorfreezed = !1, py = s.getScrollTop(!0), 0 > i && 0 >= py)return s.noticeCursor();
                if (i > s.page.maxh && py >= s.page.maxh)return s.checkContentSize(), s.noticeCursor();
                s.doScrollTop(i)
            };
            this.doScrollLeftBy = function (n, t) {
                var i = 0, i = t ? Math.floor((s.scroll.x - n) * s.scrollratio.x) : (s.timer ? s.newscrollx : s.getScrollLeft(!0)) - n, r;
                if (s.bouncescroll && (r = Math.round(s.view.w / 2), i < -r ? i = -r : i > s.page.maxw + r && (i = s.page.maxw + r)), s.cursorfreezed = !1, px = s.getScrollLeft(!0), 0 > i && 0 >= px || i > s.page.maxw && px >= s.page.maxw)return s.noticeCursor();
                s.doScrollLeft(i)
            };
            this.doScrollTo = function (n, t) {
                t && Math.round(n * s.scrollratio.y);
                s.cursorfreezed = !1;
                s.doScrollTop(n)
            };
            this.checkContentSize = function () {
                var n = s.getContentSize();
                (n.h != s.page.h || n.w != s.page.w) && s.resize(!1, n)
            };
            s.onscroll = function () {
                s.rail.drag || s.cursorfreezed || s.synched("scroll", function () {
                    s.scroll.y = Math.round(s.getScrollTop() * (1 / s.scrollratio.y));
                    s.railh && (s.scroll.x = Math.round(s.getScrollLeft() * (1 / s.scrollratio.x)));
                    s.noticeCursor()
                })
            };
            s.bind(s.docscroll, "scroll", s.onscroll);
            this.doZoomIn = function (t) {
                var i, f, e, u;
                if (!s.zoomactive) {
                    s.zoomactive = !0;
                    s.zoomrestore = {style: {}};
                    i = "position top left zIndex backgroundColor marginTop marginBottom marginLeft marginRight".split(" ");
                    f = s.win[0].style;
                    for (e in i)u = i[e], s.zoomrestore.style[u] = "undefined" != typeof f[u] ? f[u] : "";
                    return s.zoomrestore.style.width = s.win.css("width"), s.zoomrestore.style.height = s.win.css("height"), s.zoomrestore.padding = {
                        w: s.win.outerWidth() - s.win.width(),
                        h: s.win.outerHeight() - s.win.height()
                    }, c.isios4 && (s.zoomrestore.scrollTop = n(window).scrollTop(), n(window).scrollTop(0)), s.win.css({
                        position: c.isios4 ? "absolute" : "fixed",
                        top: 0,
                        left: 0,
                        "z-index": r + 100,
                        margin: "0px"
                    }), i = s.win.css("backgroundColor"), ("" == i || /transparent|rgba\(0, 0, 0, 0\)|rgba\(0,0,0,0\)/.test(i)) && s.win.css("backgroundColor", "#fff"), s.rail.css({"z-index": r + 101}), s.zoom.css({"z-index": r + 102}), s.zoom.css("backgroundPosition", "0px -18px"), s.resizeZoom(), s.onzoomin && s.onzoomin.call(s), s.cancelEvent(t)
                }
            };
            this.doZoomOut = function (t) {
                if (s.zoomactive)return s.zoomactive = !1, s.win.css("margin", ""), s.win.css(s.zoomrestore.style), c.isios4 && n(window).scrollTop(s.zoomrestore.scrollTop), s.rail.css({"z-index": s.zindex}), s.zoom.css({"z-index": s.zindex}), s.zoomrestore = !1, s.zoom.css("backgroundPosition", "0px 0px"), s.onResize(), s.onzoomout && s.onzoomout.call(s), s.cancelEvent(t)
            };
            this.doZoom = function (n) {
                return s.zoomactive ? s.doZoomOut(n) : s.doZoomIn(n)
            };
            this.resizeZoom = function () {
                if (s.zoomactive) {
                    var t = s.getScrollTop();
                    s.win.css({
                        width: n(window).width() - s.zoomrestore.padding.w + "px",
                        height: n(window).height() - s.zoomrestore.padding.h + "px"
                    });
                    s.onResize();
                    s.setScrollTop(Math.min(s.page.maxh, t))
                }
            };
            this.init();
            n.nicescroll.push(this)
        }, v = function (n) {
            var t = this;
            this.nc = n;
            this.steptime = this.lasttime = this.speedy = this.speedx = this.lasty = this.lastx = 0;
            this.snapy = this.snapx = !1;
            this.demuly = this.demulx = 0;
            this.lastscrolly = this.lastscrollx = -1;
            this.timer = this.chky = this.chkx = 0;
            this.time = function () {
                return +new Date
            };
            this.reset = function (n, i) {
                t.stop();
                var r = t.time();
                t.steptime = 0;
                t.lasttime = r;
                t.speedx = 0;
                t.speedy = 0;
                t.lastx = n;
                t.lasty = i;
                t.lastscrollx = -1;
                t.lastscrolly = -1
            };
            this.update = function (n, i) {
                var r = t.time();
                t.steptime = r - t.lasttime;
                t.lasttime = r;
                var r = i - t.lasty, e = n - t.lastx, u = t.nc.getScrollTop(), f = t.nc.getScrollLeft(), u = u + r, f = f + e;
                t.snapx = 0 > f || f > t.nc.page.maxw;
                t.snapy = 0 > u || u > t.nc.page.maxh;
                t.speedx = e;
                t.speedy = r;
                t.lastx = n;
                t.lasty = i
            };
            this.stop = function () {
                t.nc.unsynched("domomentum2d");
                t.timer && clearTimeout(t.timer);
                t.timer = 0;
                t.lastscrollx = -1;
                t.lastscrolly = -1
            };
            this.doSnapy = function (n, i) {
                var r = !1;
                0 > i ? (i = 0, r = !0) : i > t.nc.page.maxh && (i = t.nc.page.maxh, r = !0);
                0 > n ? (n = 0, r = !0) : n > t.nc.page.maxw && (n = t.nc.page.maxw, r = !0);
                r && t.nc.doScrollPos(n, i, t.nc.opt.snapbackspeed)
            };
            this.doMomentum = function (n) {
                var e = t.time(), u = n ? e + n : t.lasttime, f;
                n = t.nc.getScrollLeft();
                var h = t.nc.getScrollTop(), o = t.nc.page.maxh, s = t.nc.page.maxw;
                if (t.speedx = 0 < s ? Math.min(60, t.speedx) : 0, t.speedy = 0 < o ? Math.min(60, t.speedy) : 0, u = u && 50 >= e - u, (0 > h || h > o || 0 > n || n > s) && (u = !1), n = t.speedx && u ? t.speedx : !1, t.speedy && u && t.speedy || n) {
                    f = Math.max(16, t.steptime);
                    50 < f && (n = f / 50, t.speedx *= n, t.speedy *= n, f = 50);
                    t.demulxy = 0;
                    t.lastscrollx = t.nc.getScrollLeft();
                    t.chkx = t.lastscrollx;
                    t.lastscrolly = t.nc.getScrollTop();
                    t.chky = t.lastscrolly;
                    var i = t.lastscrollx, r = t.lastscrolly, c = function () {
                        var n = 600 < t.time() - e ? .04 : .02;
                        t.speedx && (i = Math.floor(t.lastscrollx - t.speedx * (1 - t.demulxy)), t.lastscrollx = i, 0 > i || i > s) && (n = .1);
                        t.speedy && (r = Math.floor(t.lastscrolly - t.speedy * (1 - t.demulxy)), t.lastscrolly = r, 0 > r || r > o) && (n = .1);
                        t.demulxy = Math.min(1, t.demulxy + n);
                        t.nc.synched("domomentum2d", function () {
                            t.speedx && (t.nc.getScrollLeft() != t.chkx && t.stop(), t.chkx = i, t.nc.setScrollLeft(i));
                            t.speedy && (t.nc.getScrollTop() != t.chky && t.stop(), t.chky = r, t.nc.setScrollTop(r));
                            t.timer || (t.nc.hideCursor(), t.doSnapy(i, r))
                        });
                        1 > t.demulxy ? t.timer = setTimeout(c, f) : (t.stop(), t.nc.hideCursor(), t.doSnapy(i, r))
                    };
                    c()
                } else t.doSnapy(t.nc.getScrollLeft(), t.nc.getScrollTop())
            }
        }, s = n.fn.scrollTop;
        n.cssHooks.pageYOffset = {
            get: function (t, i) {
                return (i = n.data(t, "__nicescroll") || !1) && i.ishwscroll ? i.getScrollTop() : s.call(t)
            }, set: function (t, i) {
                var r = n.data(t, "__nicescroll") || !1;
                return r && r.ishwscroll ? r.setScrollTop(parseInt(i)) : s.call(t, i), this
            }
        };
        n.fn.scrollTop = function (t) {
            if ("undefined" == typeof t) {
                var i = this[0] ? n.data(this[0], "__nicescroll") || !1 : !1;
                return i && i.ishwscroll ? i.getScrollTop() : s.call(this)
            }
            return this.each(function () {
                var i = n.data(this, "__nicescroll") || !1;
                i && i.ishwscroll ? i.setScrollTop(parseInt(t)) : s.call(n(this), t)
            })
        };
        u = n.fn.scrollLeft;
        n.cssHooks.pageXOffset = {
            get: function (t, i) {
                return (i = n.data(t, "__nicescroll") || !1) && i.ishwscroll ? i.getScrollLeft() : u.call(t)
            }, set: function (t, i) {
                var r = n.data(t, "__nicescroll") || !1;
                return r && r.ishwscroll ? r.setScrollLeft(parseInt(i)) : u.call(t, i), this
            }
        };
        n.fn.scrollLeft = function (t) {
            if ("undefined" == typeof t) {
                var i = this[0] ? n.data(this[0], "__nicescroll") || !1 : !1;
                return i && i.ishwscroll ? i.getScrollLeft() : u.call(this)
            }
            return this.each(function () {
                var i = n.data(this, "__nicescroll") || !1;
                i && i.ishwscroll ? i.setScrollLeft(parseInt(t)) : u.call(n(this), t)
            })
        };
        f = function (t) {
            var i = this, r;
            if (this.length = 0, this.name = "nicescrollarray", this.each = function (n) {
                    for (var t = 0; t < i.length; t++)n.call(i[t]);
                    return i
                }, this.push = function (n) {
                    i[i.length] = n;
                    i.length++
                }, this.eq = function (n) {
                    return i[n]
                }, t)for (a = 0; a < t.length; a++)r = n.data(t[a], "__nicescroll") || !1, r && (this[this.length] = r, this.length++);
            return this
        }, function (n, t, i) {
            for (var r = 0; r < t.length; r++)i(n, t[r])
        }(f.prototype, "show hide toggle onResize resize remove stop doScrollPos".split(" "), function (n, t) {
            n[t] = function () {
                var n = arguments;
                return this.each(function () {
                    this[t].apply(this, n)
                })
            }
        });
        n.fn.getNiceScroll = function (t) {
            return "undefined" == typeof t ? new f(this) : n.data(this[t], "__nicescroll") || !1
        };
        n.extend(n.expr[":"], {
            nicescroll: function (t) {
                return n.data(t, "__nicescroll") ? !0 : !1
            }
        });
        n.fn.niceScroll = function (t, i) {
            var r, u;
            return "undefined" != typeof i || "object" != typeof t || "jquery"in t || (i = t, t = !1), r = new f, "undefined" == typeof i && (i = {}), t && (i.doc = n(t), i.win = n(this)), u = !("doc"in i), u || "win"in i || (i.win = n(this)), this.each(function () {
                var t = n(this).data("__nicescroll") || !1;
                t || (i.doc = u ? n(this) : i.doc, t = new k(i, n(this)), n(this).data("__nicescroll", t));
                r.push(t)
            }), 1 == r.length ? r[0] : r
        };
        window.NiceScroll = {
            getjQuery: function () {
                return n
            }
        };
        n.nicescroll || (n.nicescroll = new f, n.nicescroll.options = l)
    }(jQuery), !function (n) {
        "function" == typeof define && define.amd ? define(["jquery"], n) : "object" == typeof exports ? n(require("jquery")) : n(jQuery)
    }(function (n, t) {
        function r(n, t, i, r) {
            return !(n.selector != t.selector || n.context != t.context || i && i.$lqguid != t.fn.$lqguid || r && r.$lqguid != t.fn2.$lqguid)
        }

        n.extend(n.fn, {
            livequery: function (t, u) {
                var f, e = this;
                return n.each(i.queries, function (n, i) {
                    if (r(e, i, t, u))return (f = i) && !1
                }), f = f || new i(e.selector, e.context, t, u), f.stopped = !1, f.run(), e
            }, expire: function (t, u) {
                var f = this;
                return n.each(i.queries, function (n, e) {
                    r(f, e, t, u) && !f.stopped && i.stop(e.id)
                }), f
            }
        });
        var i = n.livequery = function (t, r, u, f) {
            var e = this;
            return e.selector = t, e.context = r, e.fn = u, e.fn2 = f, e.elements = n([]), e.stopped = !1, e.id = i.queries.push(e) - 1, u.$lqguid = u.$lqguid || i.guid++, f && (f.$lqguid = f.$lqguid || i.guid++), e
        };
        i.prototype = {
            stop: function () {
                var t = this;
                t.stopped || (t.fn2 && t.elements.each(t.fn2), t.elements = n([]), t.stopped = !0)
            }, run: function () {
                var t = this;
                if (!t.stopped) {
                    var r = t.elements, i = n(t.selector, t.context), u = i.not(r), f = r.not(i);
                    t.elements = i;
                    u.each(t.fn);
                    t.fn2 && f.each(t.fn2)
                }
            }
        };
        n.extend(i, {
            guid: 0,
            queries: [],
            queue: [],
            running: !1,
            timeout: null,
            registered: [],
            checkQueue: function () {
                if (i.running && i.queue.length)for (var n = i.queue.length; n--;)i.queries[i.queue.shift()].run()
            },
            pause: function () {
                i.running = !1
            },
            play: function () {
                i.running = !0;
                i.run()
            },
            registerPlugin: function () {
                n.each(arguments, function (t, r) {
                    if (n.fn[r] && !(n.inArray(r, i.registered) > 0)) {
                        var u = n.fn[r];
                        n.fn[r] = function () {
                            var n = u.apply(this, arguments);
                            return i.run(), n
                        };
                        i.registered.push(r)
                    }
                })
            },
            run: function (r) {
                r !== t ? n.inArray(r, i.queue) < 0 && i.queue.push(r) : n.each(i.queries, function (t) {
                    n.inArray(t, i.queue) < 0 && i.queue.push(t)
                });
                i.timeout && clearTimeout(i.timeout);
                i.timeout = setTimeout(i.checkQueue, 20)
            },
            stop: function (r) {
                r !== t ? i.queries[r].stop() : n.each(i.queries, i.prototype.stop)
            }
        });
        i.registerPlugin("append", "prepend", "after", "before", "wrap", "attr", "removeAttr", "addClass", "removeClass", "toggleClass", "empty", "remove", "html", "prop", "removeProp");
        n(function () {
            i.play()
        })
    }), !jQuery)throw new Error("Bootstrap requires jQuery");
+function (n) {
    "use strict";
    function t() {
        var i = document.createElement("bootstrap"), t = {
            WebkitTransition: "webkitTransitionEnd",
            MozTransition: "transitionend",
            OTransition: "oTransitionEnd otransitionend",
            transition: "transitionend"
        }, n;
        for (n in t)if (void 0 !== i.style[n])return {end: t[n]}
    }

    n.fn.emulateTransitionEnd = function (t) {
        var i = !1, u = this, r;
        n(this).one(n.support.transition.end, function () {
            i = !0
        });
        return r = function () {
            i || n(u).trigger(n.support.transition.end)
        }, setTimeout(r, t), this
    };
    n(function () {
        n.support.transition = t()
    })
}(window.jQuery);
+function (n) {
    "use strict";
    var i = '[data-dismiss="alert"]', t = function (t) {
        n(t).on("click", i, this.close)
    }, r;
    t.prototype.close = function (t) {
        function f() {
            i.trigger("closed.bs.alert").remove()
        }

        var u = n(this), r = u.attr("data-target"), i;
        r || (r = u.attr("href"), r = r && r.replace(/.*(?=#[^\s]*$)/, ""));
        i = n(r);
        t && t.preventDefault();
        i.length || (i = u.hasClass("alert") ? u : u.parent());
        i.trigger(t = n.Event("close.bs.alert"));
        t.isDefaultPrevented() || (i.removeClass("in"), n.support.transition && i.hasClass("fade") ? i.one(n.support.transition.end, f).emulateTransitionEnd(150) : f())
    };
    r = n.fn.alert;
    n.fn.alert = function (i) {
        return this.each(function () {
            var r = n(this), u = r.data("bs.alert");
            u || r.data("bs.alert", u = new t(this));
            "string" == typeof i && u[i].call(r)
        })
    };
    n.fn.alert.Constructor = t;
    n.fn.alert.noConflict = function () {
        return n.fn.alert = r, this
    };
    n(document).on("click.bs.alert.data-api", i, t.prototype.close)
}(window.jQuery);
+function (n) {
    "use strict";
    var t = function (i, r) {
        this.$element = n(i);
        this.options = n.extend({}, t.DEFAULTS, r)
    }, i;
    t.DEFAULTS = {loadingText: "loading..."};
    t.prototype.setState = function (n) {
        var i = "disabled", t = this.$element, r = t.is("input") ? "val" : "html", u = t.data();
        n += "Text";
        u.resetText || t.data("resetText", t[r]());
        t[r](u[n] || this.options[n]);
        setTimeout(function () {
            "loadingText" == n ? t.addClass(i).attr(i, i) : t.removeClass(i).removeAttr(i)
        }, 0)
    };
    t.prototype.toggle = function () {
        var n = this.$element.closest('[data-toggle="buttons"]'), t;
        n.length && (t = this.$element.find("input").prop("checked", !this.$element.hasClass("active")).trigger("change"), "radio" === t.prop("type") && n.find(".active").removeClass("active"));
        this.$element.toggleClass("active")
    };
    i = n.fn.button;
    n.fn.button = function (i) {
        return this.each(function () {
            var u = n(this), r = u.data("bs.button"), f = "object" == typeof i && i;
            r || u.data("bs.button", r = new t(this, f));
            "toggle" == i ? r.toggle() : i && r.setState(i)
        })
    };
    n.fn.button.Constructor = t;
    n.fn.button.noConflict = function () {
        return n.fn.button = i, this
    };
    n(document).on("click.bs.button.data-api", "[data-toggle^=button]", function (t) {
        var i = n(t.target);
        i.hasClass("btn") || (i = i.closest(".btn"));
        i.button("toggle");
        t.preventDefault()
    })
}(window.jQuery);
+function (n) {
    "use strict";
    var t = function (t, i) {
        this.$element = n(t);
        this.$indicators = this.$element.find(".carousel-indicators");
        this.options = i;
        this.paused = this.sliding = this.interval = this.$active = this.$items = null;
        "hover" == this.options.pause && this.$element.on("mouseenter", n.proxy(this.pause, this)).on("mouseleave", n.proxy(this.cycle, this))
    }, i;
    t.DEFAULTS = {interval: 5e3, pause: "hover", wrap: !0};
    t.prototype.cycle = function (t) {
        return t || (this.paused = !1), this.interval && clearInterval(this.interval), this.options.interval && !this.paused && (this.interval = setInterval(n.proxy(this.next, this), this.options.interval)), this
    };
    t.prototype.getActiveIndex = function () {
        return this.$active = this.$element.find(".item.active"), this.$items = this.$active.parent().children(), this.$items.index(this.$active)
    };
    t.prototype.to = function (t) {
        var r = this, i = this.getActiveIndex();
        if (!(t > this.$items.length - 1) && !(0 > t))return this.sliding ? this.$element.one("slid", function () {
            r.to(t)
        }) : i == t ? this.pause().cycle() : this.slide(t > i ? "next" : "prev", n(this.$items[t]))
    };
    t.prototype.pause = function (t) {
        return t || (this.paused = !0), this.$element.find(".next, .prev").length && n.support.transition.end && (this.$element.trigger(n.support.transition.end), this.cycle(!0)), this.interval = clearInterval(this.interval), this
    };
    t.prototype.next = function () {
        if (!this.sliding)return this.slide("next")
    };
    t.prototype.prev = function () {
        if (!this.sliding)return this.slide("prev")
    };
    t.prototype.slide = function (t, i) {
        var u = this.$element.find(".item.active"), r = i || u[t](), s = this.interval, f = "next" == t ? "left" : "right", h = "next" == t ? "first" : "last", o = this, e;
        if (!r.length) {
            if (!this.options.wrap)return;
            r = this.$element.find(".item")[h]()
        }
        if (this.sliding = !0, s && this.pause(), e = n.Event("slide.bs.carousel", {
                relatedTarget: r[0],
                direction: f
            }), !r.hasClass("active")) {
            if (this.$indicators.length && (this.$indicators.find(".active").removeClass("active"), this.$element.one("slid", function () {
                    var t = n(o.$indicators.children()[o.getActiveIndex()]);
                    t && t.addClass("active")
                })), n.support.transition && this.$element.hasClass("slide")) {
                if (this.$element.trigger(e), e.isDefaultPrevented())return;
                r.addClass(t);
                r[0].offsetWidth;
                u.addClass(f);
                r.addClass(f);
                u.one(n.support.transition.end, function () {
                    r.removeClass([t, f].join(" ")).addClass("active");
                    u.removeClass(["active", f].join(" "));
                    o.sliding = !1;
                    setTimeout(function () {
                        o.$element.trigger("slid")
                    }, 0)
                }).emulateTransitionEnd(600)
            } else {
                if (this.$element.trigger(e), e.isDefaultPrevented())return;
                u.removeClass("active");
                r.addClass("active");
                this.sliding = !1;
                this.$element.trigger("slid")
            }
            return s && this.cycle(), this
        }
    };
    i = n.fn.carousel;
    n.fn.carousel = function (i) {
        return this.each(function () {
            var u = n(this), r = u.data("bs.carousel"), f = n.extend({}, t.DEFAULTS, u.data(), "object" == typeof i && i), e = "string" == typeof i ? i : f.slide;
            r || u.data("bs.carousel", r = new t(this, f));
            "number" == typeof i ? r.to(i) : e ? r[e]() : f.interval && r.pause().cycle()
        })
    };
    n.fn.carousel.Constructor = t;
    n.fn.carousel.noConflict = function () {
        return n.fn.carousel = i, this
    };
    n(document).on("click.bs.carousel.data-api", "[data-slide], [data-slide-to]", function (t) {
        var f, i = n(this), r = n(i.attr("data-target") || (f = i.attr("href")) && f.replace(/.*(?=#[^\s]+$)/, "")), e = n.extend({}, r.data(), i.data()), u = i.attr("data-slide-to");
        u && (e.interval = !1);
        r.carousel(e);
        (u = i.attr("data-slide-to")) && r.data("bs.carousel").to(u);
        t.preventDefault()
    });
    n(window).on("load", function () {
        n('[data-ride="carousel"]').each(function () {
            var t = n(this);
            t.carousel(t.data())
        })
    })
}(window.jQuery);
+function (n) {
    "use strict";
    var t = function (i, r) {
        this.$element = n(i);
        this.options = n.extend({}, t.DEFAULTS, r);
        this.transitioning = null;
        this.options.parent && (this.$parent = n(this.options.parent));
        this.options.toggle && this.toggle()
    }, i;
    t.DEFAULTS = {toggle: !0};
    t.prototype.dimension = function () {
        var n = this.$element.hasClass("width");
        return n ? "width" : "height"
    };
    t.prototype.show = function () {
        var u, t, r, i, f, e;
        if (!this.transitioning && !this.$element.hasClass("in") && (u = n.Event("show.bs.collapse"), this.$element.trigger(u), !u.isDefaultPrevented())) {
            if (t = this.$parent && this.$parent.find("> .panel > .in"), t && t.length) {
                if (r = t.data("bs.collapse"), r && r.transitioning)return;
                t.collapse("hide");
                r || t.data("bs.collapse", null)
            }
            if (i = this.dimension(), this.$element.removeClass("collapse").addClass("collapsing")[i](0), this.transitioning = 1, f = function () {
                    this.$element.removeClass("collapsing").addClass("in")[i]("auto");
                    this.transitioning = 0;
                    this.$element.trigger("shown.bs.collapse")
                }, !n.support.transition)return f.call(this);
            e = n.camelCase(["scroll", i].join("-"));
            this.$element.one(n.support.transition.end, n.proxy(f, this)).emulateTransitionEnd(350)[i](this.$element[0][e])
        }
    };
    t.prototype.hide = function () {
        var i, t, r;
        if (!this.transitioning && this.$element.hasClass("in") && (i = n.Event("hide.bs.collapse"), this.$element.trigger(i), !i.isDefaultPrevented()))return t = this.dimension(), this.$element[t](this.$element[t]())[0].offsetHeight, this.$element.addClass("collapsing").removeClass("collapse").removeClass("in"), this.transitioning = 1, r = function () {
            this.transitioning = 0;
            this.$element.trigger("hidden.bs.collapse").removeClass("collapsing").addClass("collapse")
        }, n.support.transition ? (this.$element[t](0).one(n.support.transition.end, n.proxy(r, this)).emulateTransitionEnd(350), void 0) : r.call(this)
    };
    t.prototype.toggle = function () {
        this[this.$element.hasClass("in") ? "hide" : "show"]()
    };
    i = n.fn.collapse;
    n.fn.collapse = function (i) {
        return this.each(function () {
            var r = n(this), u = r.data("bs.collapse"), f = n.extend({}, t.DEFAULTS, r.data(), "object" == typeof i && i);
            u || r.data("bs.collapse", u = new t(this, f));
            "string" == typeof i && u[i]()
        })
    };
    n.fn.collapse.Constructor = t;
    n.fn.collapse.noConflict = function () {
        return n.fn.collapse = i, this
    };
    n(document).on("click.bs.collapse.data-api", "[data-toggle=collapse]", function (t) {
        var e, i = n(this), s = i.attr("data-target") || t.preventDefault() || (e = i.attr("href")) && e.replace(/.*(?=#[^\s]+$)/, ""), r = n(s), u = r.data("bs.collapse"), h = u ? "toggle" : i.data(), f = i.attr("data-parent"), o = f && n(f);
        u && u.transitioning || (o && o.find('[data-toggle=collapse][data-parent="' + f + '"]').not(i).addClass("collapsed"), i[r.hasClass("in") ? "addClass" : "removeClass"]("collapsed"));
        r.collapse(h)
    })
}(window.jQuery);
+function (n) {
    "use strict";
    function r() {
        n(e).remove();
        n(i).each(function (t) {
            var i = u(n(this));
            i.hasClass("open") && (i.trigger(t = n.Event("hide.bs.dropdown")), t.isDefaultPrevented() || i.removeClass("open").trigger("hidden.bs.dropdown"))
        })
    }

    function u(t) {
        var i = t.attr("data-target"), r;
        return i || (i = t.attr("href"), i = i && /#/.test(i) && i.replace(/.*(?=#[^\s]*$)/, "")), r = i && n(i), r && r.length ? r : t.parent()
    }

    var e = ".dropdown-backdrop", i = "[data-toggle=dropdown]", t = function (t) {
        n(t).on("click.bs.dropdown", this.toggle)
    }, f;
    t.prototype.toggle = function (t) {
        var f = n(this), i, e;
        if (!f.is(".disabled, :disabled")) {
            if (i = u(f), e = i.hasClass("open"), r(), !e) {
                if ("ontouchstart"in document.documentElement && !i.closest(".navbar-nav").length && n('<div class="dropdown-backdrop"/>').insertAfter(n(this)).on("click", r), i.trigger(t = n.Event("show.bs.dropdown")), t.isDefaultPrevented())return;
                i.toggleClass("open").trigger("shown.bs.dropdown");
                f.focus()
            }
            return !1
        }
    };
    t.prototype.keydown = function (t) {
        var e, o, s, f, r;
        if (/(38|40|27)/.test(t.keyCode) && (e = n(this), t.preventDefault(), t.stopPropagation(), !e.is(".disabled, :disabled"))) {
            if (o = u(e), s = o.hasClass("open"), !s || s && 27 == t.keyCode)return 27 == t.which && o.find(i).focus(), e.click();
            f = n("[role=menu] li:not(.divider):visible a", o);
            f.length && (r = f.index(f.filter(":focus")), 38 == t.keyCode && r > 0 && r--, 40 == t.keyCode && r < f.length - 1 && r++, ~r || (r = 0), f.eq(r).focus())
        }
    };
    f = n.fn.dropdown;
    n.fn.dropdown = function (i) {
        return this.each(function () {
            var r = n(this), u = r.data("dropdown");
            u || r.data("dropdown", u = new t(this));
            "string" == typeof i && u[i].call(r)
        })
    };
    n.fn.dropdown.Constructor = t;
    n.fn.dropdown.noConflict = function () {
        return n.fn.dropdown = f, this
    };
    n(document).on("click.bs.dropdown.data-api", r).on("click.bs.dropdown.data-api", ".dropdown form", function (n) {
        n.stopPropagation()
    }).on("click.bs.dropdown.data-api", i, t.prototype.toggle).on("keydown.bs.dropdown.data-api", i + ", [role=menu]", t.prototype.keydown)
}(window.jQuery);
+function (n) {
    "use strict";
    var t = function (t, i) {
        this.options = i;
        this.$element = n(t);
        this.$backdrop = this.isShown = null;
        this.options.remote && this.$element.load(this.options.remote)
    }, i;
    t.DEFAULTS = {backdrop: !0, keyboard: !0, show: !0};
    t.prototype.toggle = function (n) {
        return this[this.isShown ? "hide" : "show"](n)
    };
    t.prototype.show = function (t) {
        var i = this, r = n.Event("show.bs.modal", {relatedTarget: t});
        this.$element.trigger(r);
        this.isShown || r.isDefaultPrevented() || (this.isShown = !0, this.escape(), this.$element.on("click.dismiss.modal", '[data-dismiss="modal"]', n.proxy(this.hide, this)), this.backdrop(function () {
            var u = n.support.transition && i.$element.hasClass("fade"), r;
            i.$element.parent().length || i.$element.appendTo(document.body);
            i.$element.show();
            u && i.$element[0].offsetWidth;
            i.$element.addClass("in").attr("aria-hidden", !1);
            i.enforceFocus();
            r = n.Event("shown.bs.modal", {relatedTarget: t});
            u ? i.$element.find(".modal-dialog").one(n.support.transition.end, function () {
                i.$element.focus().trigger(r)
            }).emulateTransitionEnd(300) : i.$element.focus().trigger(r)
        }))
    };
    t.prototype.hide = function (t) {
        t && t.preventDefault();
        t = n.Event("hide.bs.modal");
        this.$element.trigger(t);
        this.isShown && !t.isDefaultPrevented() && (this.isShown = !1, this.escape(), n(document).off("focusin.bs.modal"), this.$element.removeClass("in").attr("aria-hidden", !0).off("click.dismiss.modal"), n.support.transition && this.$element.hasClass("fade") ? this.$element.one(n.support.transition.end, n.proxy(this.hideModal, this)).emulateTransitionEnd(300) : this.hideModal())
    };
    t.prototype.enforceFocus = function () {
        n(document).off("focusin.bs.modal").on("focusin.bs.modal", n.proxy(function (n) {
            this.$element[0] === n.target || this.$element.has(n.target).length || this.$element.focus()
        }, this))
    };
    t.prototype.escape = function () {
        this.isShown && this.options.keyboard ? this.$element.on("keyup.dismiss.bs.modal", n.proxy(function (n) {
            27 == n.which && this.hide()
        }, this)) : this.isShown || this.$element.off("keyup.dismiss.bs.modal")
    };
    t.prototype.hideModal = function () {
        var n = this;
        this.$element.hide();
        this.backdrop(function () {
            n.removeBackdrop();
            n.$element.trigger("hidden.bs.modal")
        })
    };
    t.prototype.removeBackdrop = function () {
        this.$backdrop && this.$backdrop.remove();
        this.$backdrop = null
    };
    t.prototype.backdrop = function (t) {
        var r = this.$element.hasClass("fade") ? "fade" : "", i;
        if (this.isShown && this.options.backdrop) {
            if (i = n.support.transition && r, this.$backdrop = n('<div class="modal-backdrop ' + r + '" />').appendTo(document.body), this.$element.on("click.dismiss.modal", n.proxy(function (n) {
                    n.target === n.currentTarget && ("static" == this.options.backdrop ? this.$element[0].focus.call(this.$element[0]) : this.hide.call(this))
                }, this)), i && this.$backdrop[0].offsetWidth, this.$backdrop.addClass("in"), !t)return;
            i ? this.$backdrop.one(n.support.transition.end, t).emulateTransitionEnd(150) : t()
        } else!this.isShown && this.$backdrop ? (this.$backdrop.removeClass("in"), n.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one(n.support.transition.end, t).emulateTransitionEnd(150) : t()) : t && t()
    };
    i = n.fn.modal;
    n.fn.modal = function (i, r) {
        return this.each(function () {
            var f = n(this), u = f.data("bs.modal"), e = n.extend({}, t.DEFAULTS, f.data(), "object" == typeof i && i);
            u || f.data("bs.modal", u = new t(this, e));
            "string" == typeof i ? u[i](r) : e.show && u.show(r)
        })
    };
    n.fn.modal.Constructor = t;
    n.fn.modal.noConflict = function () {
        return n.fn.modal = i, this
    };
    n(document).on("click.bs.modal.data-api", '[data-toggle="modal"]', function (t) {
        var i = n(this), r = i.attr("href"), u = n(i.attr("data-target") || r && r.replace(/.*(?=#[^\s]+$)/, "")), f = u.data("modal") ? "toggle" : n.extend({remote: !/#/.test(r) && r}, u.data(), i.data());
        t.preventDefault();
        u.modal(f, this).one("hide", function () {
            i.is(":visible") && i.focus()
        })
    });
    n(document).on("show.bs.modal", ".modal", function () {
        n(document.body).addClass("modal-open")
    }).on("hidden.bs.modal", ".modal", function () {
        n(document.body).removeClass("modal-open")
    })
}(window.jQuery);
+function (n) {
    "use strict";
    var t = function (n, t) {
        this.type = this.options = this.enabled = this.timeout = this.hoverState = this.$element = null;
        this.init("tooltip", n, t)
    }, i;
    t.DEFAULTS = {
        animation: !0,
        placement: "top",
        selector: !1,
        template: '<div class="tooltip"><div class="tooltip-arrow"><\/div><div class="tooltip-inner"><\/div><\/div>',
        trigger: "hover focus",
        title: "",
        delay: 0,
        html: !1,
        container: !1
    };
    t.prototype.init = function (t, i, r) {
        var f, e, u, o, s;
        for (this.enabled = !0, this.type = t, this.$element = n(i), this.options = this.getOptions(r), f = this.options.trigger.split(" "), e = f.length; e--;)if (u = f[e], "click" == u)this.$element.on("click." + this.type, this.options.selector, n.proxy(this.toggle, this)); else"manual" != u && (o = "hover" == u ? "mouseenter" : "focus", s = "hover" == u ? "mouseleave" : "blur", this.$element.on(o + "." + this.type, this.options.selector, n.proxy(this.enter, this)), this.$element.on(s + "." + this.type, this.options.selector, n.proxy(this.leave, this)));
        this.options.selector ? this._options = n.extend({}, this.options, {
            trigger: "manual",
            selector: ""
        }) : this.fixTitle()
    };
    t.prototype.getDefaults = function () {
        return t.DEFAULTS
    };
    t.prototype.getOptions = function (t) {
        return t = n.extend({}, this.getDefaults(), this.$element.data(), t), t.delay && "number" == typeof t.delay && (t.delay = {
            show: t.delay,
            hide: t.delay
        }), t
    };
    t.prototype.getDelegateOptions = function () {
        var t = {}, i = this.getDefaults();
        return this._options && n.each(this._options, function (n, r) {
            i[n] != r && (t[n] = r)
        }), t
    };
    t.prototype.enter = function (t) {
        var i = t instanceof this.constructor ? t : n(t.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type);
        return clearTimeout(i.timeout), i.hoverState = "in", i.options.delay && i.options.delay.show ? (i.timeout = setTimeout(function () {
            "in" == i.hoverState && i.show()
        }, i.options.delay.show), void 0) : i.show()
    };
    t.prototype.leave = function (t) {
        var i = t instanceof this.constructor ? t : n(t.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type);
        return clearTimeout(i.timeout), i.hoverState = "out", i.options.delay && i.options.delay.hide ? (i.timeout = setTimeout(function () {
            "out" == i.hoverState && i.hide()
        }, i.options.delay.hide), void 0) : i.hide()
    };
    t.prototype.show = function () {
        var o = n.Event("show.bs." + this.type), i, l;
        if (this.hasContent() && this.enabled) {
            if (this.$element.trigger(o), o.isDefaultPrevented())return;
            i = this.tip();
            this.setContent();
            this.options.animation && i.addClass("fade");
            var t = "function" == typeof this.options.placement ? this.options.placement.call(this, i[0], this.$element[0]) : this.options.placement, s = /\s?auto?\s?/i, h = s.test(t);
            h && (t = t.replace(s, "") || "top");
            i.detach().css({top: 0, left: 0, display: "block"}).addClass(t);
            this.options.container ? i.appendTo(this.options.container) : i.insertAfter(this.$element);
            var r = this.getPosition(), u = i[0].offsetWidth, f = i[0].offsetHeight;
            if (h) {
                var e = this.$element.parent(), a = t, c = document.documentElement.scrollTop || document.body.scrollTop, v = "body" == this.options.container ? window.innerWidth : e.outerWidth(), y = "body" == this.options.container ? window.innerHeight : e.outerHeight(), p = "body" == this.options.container ? 0 : e.offset().left;
                t = "bottom" == t && r.top + r.height + f - c > y ? "top" : "top" == t && r.top - c - f < 0 ? "bottom" : "right" == t && r.right + u > v ? "left" : "left" == t && r.left - u < p ? "right" : t;
                i.removeClass(a).addClass(t)
            }
            l = this.getCalculatedOffset(t, r, u, f);
            this.applyPlacement(l, t);
            this.$element.trigger("shown.bs." + this.type)
        }
    };
    t.prototype.applyPlacement = function (n, t) {
        var h, i = this.tip(), c = i[0].offsetWidth, f = i[0].offsetHeight, e = parseInt(i.css("margin-top"), 10), o = parseInt(i.css("margin-left"), 10), u, r, s;
        isNaN(e) && (e = 0);
        isNaN(o) && (o = 0);
        n.top = n.top + e;
        n.left = n.left + o;
        i.offset(n).addClass("in");
        u = i[0].offsetWidth;
        r = i[0].offsetHeight;
        ("top" == t && r != f && (h = !0, n.top = n.top + f - r), /bottom|top/.test(t)) ? (s = 0, n.left < 0 && (s = -2 * n.left, n.left = 0, i.offset(n), u = i[0].offsetWidth, r = i[0].offsetHeight), this.replaceArrow(s - c + u, u, "left")) : this.replaceArrow(r - f, r, "top");
        h && i.offset(n)
    };
    t.prototype.replaceArrow = function (n, t, i) {
        this.arrow().css(i, n ? 50 * (1 - n / t) + "%" : "")
    };
    t.prototype.setContent = function () {
        var n = this.tip(), t = this.getTitle();
        n.find(".tooltip-inner")[this.options.html ? "html" : "text"](t);
        n.removeClass("fade in top bottom left right")
    };
    t.prototype.hide = function () {
        function i() {
            "in" != u.hoverState && t.detach()
        }

        var u = this, t = this.tip(), r = n.Event("hide.bs." + this.type);
        return this.$element.trigger(r), r.isDefaultPrevented() ? void 0 : (t.removeClass("in"), n.support.transition && this.$tip.hasClass("fade") ? t.one(n.support.transition.end, i).emulateTransitionEnd(150) : i(), this.$element.trigger("hidden.bs." + this.type), this)
    };
    t.prototype.fixTitle = function () {
        var n = this.$element;
        (n.attr("title") || "string" != typeof n.attr("data-original-title")) && n.attr("data-original-title", n.attr("title") || "").attr("title", "")
    };
    t.prototype.hasContent = function () {
        return this.getTitle()
    };
    t.prototype.getPosition = function () {
        var t = this.$element[0];
        return n.extend({}, "function" == typeof t.getBoundingClientRect ? t.getBoundingClientRect() : {
            width: t.offsetWidth,
            height: t.offsetHeight
        }, this.$element.offset())
    };
    t.prototype.getCalculatedOffset = function (n, t, i, r) {
        return "bottom" == n ? {
            top: t.top + t.height,
            left: t.left + t.width / 2 - i / 2
        } : "top" == n ? {
            top: t.top - r,
            left: t.left + t.width / 2 - i / 2
        } : "left" == n ? {top: t.top + t.height / 2 - r / 2, left: t.left - i} : {
            top: t.top + t.height / 2 - r / 2,
            left: t.left + t.width
        }
    };
    t.prototype.getTitle = function () {
        var t = this.$element, n = this.options;
        return t.attr("data-original-title") || ("function" == typeof n.title ? n.title.call(t[0]) : n.title)
    };
    t.prototype.tip = function () {
        return this.$tip = this.$tip || n(this.options.template)
    };
    t.prototype.arrow = function () {
        return this.$arrow = this.$arrow || this.tip().find(".tooltip-arrow")
    };
    t.prototype.validate = function () {
        this.$element[0].parentNode || (this.hide(), this.$element = null, this.options = null)
    };
    t.prototype.enable = function () {
        this.enabled = !0
    };
    t.prototype.disable = function () {
        this.enabled = !1
    };
    t.prototype.toggleEnabled = function () {
        this.enabled = !this.enabled
    };
    t.prototype.toggle = function (t) {
        var i = t ? n(t.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type) : this;
        i.tip().hasClass("in") ? i.leave(i) : i.enter(i)
    };
    t.prototype.destroy = function () {
        this.hide().$element.off("." + this.type).removeData("bs." + this.type)
    };
    i = n.fn.tooltip;
    n.fn.tooltip = function (i) {
        return this.each(function () {
            var u = n(this), r = u.data("bs.tooltip"), f = "object" == typeof i && i;
            r || u.data("bs.tooltip", r = new t(this, f));
            "string" == typeof i && r[i]()
        })
    };
    n.fn.tooltip.Constructor = t;
    n.fn.tooltip.noConflict = function () {
        return n.fn.tooltip = i, this
    }
}(window.jQuery);
+function (n) {
    "use strict";
    var t = function (n, t) {
        this.init("popover", n, t)
    }, i;
    if (!n.fn.tooltip)throw new Error("Popover requires tooltip.js");
    t.DEFAULTS = n.extend({}, n.fn.tooltip.Constructor.DEFAULTS, {
        placement: "right",
        trigger: "click",
        content: "",
        template: '<div class="popover"><div class="arrow"><\/div><h3 class="popover-title"><\/h3><div class="popover-content"><\/div><\/div>'
    });
    t.prototype = n.extend({}, n.fn.tooltip.Constructor.prototype);
    t.prototype.constructor = t;
    t.prototype.getDefaults = function () {
        return t.DEFAULTS
    };
    t.prototype.setContent = function () {
        var n = this.tip(), t = this.getTitle(), i = this.getContent();
        n.find(".popover-title")[this.options.html ? "html" : "text"](t);
        n.find(".popover-content")[this.options.html ? "html" : "text"](i);
        n.removeClass("fade top bottom left right in");
        n.find(".popover-title").html() || n.find(".popover-title").hide()
    };
    t.prototype.hasContent = function () {
        return this.getTitle() || this.getContent()
    };
    t.prototype.getContent = function () {
        var t = this.$element, n = this.options;
        return t.attr("data-content") || ("function" == typeof n.content ? n.content.call(t[0]) : n.content)
    };
    t.prototype.arrow = function () {
        return this.$arrow = this.$arrow || this.tip().find(".arrow")
    };
    t.prototype.tip = function () {
        return this.$tip || (this.$tip = n(this.options.template)), this.$tip
    };
    i = n.fn.popover;
    n.fn.popover = function (i) {
        return this.each(function () {
            var u = n(this), r = u.data("bs.popover"), f = "object" == typeof i && i;
            r || u.data("bs.popover", r = new t(this, f));
            "string" == typeof i && r[i]()
        })
    };
    n.fn.popover.Constructor = t;
    n.fn.popover.noConflict = function () {
        return n.fn.popover = i, this
    }
}(window.jQuery);
+function (n) {
    "use strict";
    function t(i, r) {
        var u, f = n.proxy(this.process, this);
        this.$element = n(i).is("body") ? n(window) : n(i);
        this.$body = n("body");
        this.$scrollElement = this.$element.on("scroll.bs.scroll-spy.data-api", f);
        this.options = n.extend({}, t.DEFAULTS, r);
        this.selector = (this.options.target || (u = n(i).attr("href")) && u.replace(/.*(?=#[^\s]+$)/, "") || "") + " .nav li > a";
        this.offsets = n([]);
        this.targets = n([]);
        this.activeTarget = null;
        this.refresh();
        this.process()
    }

    t.DEFAULTS = {offset: 10};
    t.prototype.refresh = function () {
        var i = this.$element[0] == window ? "offset" : "position", t;
        this.offsets = n([]);
        this.targets = n([]);
        t = this;
        this.$body.find(this.selector).map(function () {
            var f = n(this), r = f.data("target") || f.attr("href"), u = /^#\w/.test(r) && n(r);
            return u && u.length && [[u[i]().top + (!n.isWindow(t.$scrollElement.get(0)) && t.$scrollElement.scrollTop()), r]] || null
        }).sort(function (n, t) {
            return n[0] - t[0]
        }).each(function () {
            t.offsets.push(this[0]);
            t.targets.push(this[1])
        })
    };
    t.prototype.process = function () {
        var n, i = this.$scrollElement.scrollTop() + this.options.offset, f = this.$scrollElement[0].scrollHeight || this.$body[0].scrollHeight, e = f - this.$scrollElement.height(), t = this.offsets, r = this.targets, u = this.activeTarget;
        if (i >= e)return u != (n = r.last()[0]) && this.activate(n);
        for (n = t.length; n--;)u != r[n] && i >= t[n] && (!t[n + 1] || i <= t[n + 1]) && this.activate(r[n])
    };
    t.prototype.activate = function (t) {
        this.activeTarget = t;
        n(this.selector).parents(".active").removeClass("active");
        var r = this.selector + '[data-target="' + t + '"],' + this.selector + '[href="' + t + '"]', i = n(r).parents("li").addClass("active");
        i.parent(".dropdown-menu").length && (i = i.closest("li.dropdown").addClass("active"));
        i.trigger("activate")
    };
    var i = n.fn.scrollspy;
    n.fn.scrollspy = function (i) {
        return this.each(function () {
            var u = n(this), r = u.data("bs.scrollspy"), f = "object" == typeof i && i;
            r || u.data("bs.scrollspy", r = new t(this, f));
            "string" == typeof i && r[i]()
        })
    };
    n.fn.scrollspy.Constructor = t;
    n.fn.scrollspy.noConflict = function () {
        return n.fn.scrollspy = i, this
    };
    n(window).on("load", function () {
        n('[data-spy="scroll"]').each(function () {
            var t = n(this);
            t.scrollspy(t.data())
        })
    })
}(window.jQuery);
+function (n) {
    "use strict";
    var t = function (t) {
        this.element = n(t)
    }, i;
    t.prototype.show = function () {
        var t = this.element, e = t.closest("ul:not(.dropdown-menu)"), i = t.attr("data-target"), r, u, f;
        (i || (i = t.attr("href"), i = i && i.replace(/.*(?=#[^\s]*$)/, "")), t.parent("li").hasClass("active")) || (r = e.find(".active:last a")[0], u = n.Event("show.bs.tab", {relatedTarget: r}), (t.trigger(u), u.isDefaultPrevented()) || (f = n(i), this.activate(t.parent("li"), e), this.activate(f, f.parent(), function () {
            t.trigger({type: "shown.bs.tab", relatedTarget: r})
        })))
    };
    t.prototype.activate = function (t, i, r) {
        function f() {
            u.removeClass("active").find("> .dropdown-menu > .active").removeClass("active");
            t.addClass("active");
            e ? (t[0].offsetWidth, t.addClass("in")) : t.removeClass("fade");
            t.parent(".dropdown-menu") && t.closest("li.dropdown").addClass("active");
            r && r()
        }

        var u = i.find("> .active"), e = r && n.support.transition && u.hasClass("fade");
        e ? u.one(n.support.transition.end, f).emulateTransitionEnd(150) : f();
        u.removeClass("in")
    };
    i = n.fn.tab;
    n.fn.tab = function (i) {
        return this.each(function () {
            var u = n(this), r = u.data("bs.tab");
            r || u.data("bs.tab", r = new t(this));
            "string" == typeof i && r[i]()
        })
    };
    n.fn.tab.Constructor = t;
    n.fn.tab.noConflict = function () {
        return n.fn.tab = i, this
    };
    n(document).on("click.bs.tab.data-api", '[data-toggle="tab"], [data-toggle="pill"]', function (t) {
        t.preventDefault();
        n(this).tab("show")
    })
}(window.jQuery);
+function (n) {
    "use strict";
    var t = function (i, r) {
        this.options = n.extend({}, t.DEFAULTS, r);
        this.$window = n(window).on("scroll.bs.affix.data-api", n.proxy(this.checkPosition, this)).on("click.bs.affix.data-api", n.proxy(this.checkPositionWithEventLoop, this));
        this.$element = n(i);
        this.affixed = this.unpin = null;
        this.checkPosition()
    }, i;
    t.RESET = "affix affix-top affix-bottom";
    t.DEFAULTS = {offset: 0};
    t.prototype.checkPositionWithEventLoop = function () {
        setTimeout(n.proxy(this.checkPosition, this), 1)
    };
    t.prototype.checkPosition = function () {
        var i;
        if (this.$element.is(":visible")) {
            var s = n(document).height(), e = this.$window.scrollTop(), o = this.$element.offset(), r = this.options.offset, f = r.top, u = r.bottom;
            "object" != typeof r && (u = f = r);
            "function" == typeof f && (f = r.top());
            "function" == typeof u && (u = r.bottom());
            i = null != this.unpin && e + this.unpin <= o.top ? !1 : null != u && o.top + this.$element.height() >= s - u ? "bottom" : null != f && f >= e ? "top" : !1;
            this.affixed !== i && (this.unpin && this.$element.css("top", ""), this.affixed = i, this.unpin = "bottom" == i ? o.top - e : null, this.$element.removeClass(t.RESET).addClass("affix" + (i ? "-" + i : "")), "bottom" == i && this.$element.offset({top: document.body.offsetHeight - u - this.$element.height()}))
        }
    };
    i = n.fn.affix;
    n.fn.affix = function (i) {
        return this.each(function () {
            var u = n(this), r = u.data("bs.affix"), f = "object" == typeof i && i;
            r || u.data("bs.affix", r = new t(this, f));
            "string" == typeof i && r[i]()
        })
    };
    n.fn.affix.Constructor = t;
    n.fn.affix.noConflict = function () {
        return n.fn.affix = i, this
    };
    n(window).on("load", function () {
        n('[data-spy="affix"]').each(function () {
            var i = n(this), t = i.data();
            t.offset = t.offset || {};
            t.offsetBottom && (t.offset.bottom = t.offsetBottom);
            t.offsetTop && (t.offset.top = t.offsetTop);
            i.affix(t)
        })
    })
}(window.jQuery);
Global = function () {
    function n() {
    }

    return n.initiateSecondNav = function () {
        var n, i, t;
        if ($(".second-nav")) {
            n = null;
            i = null;
            switch (window.location.pathname) {
                case"/posts":
                    n = $("#postslink");
                    n.addClass("active");
                    break;
                case"/feed":
                    n = $("#feedlink");
                    n.addClass("active")
            }
            n = $("#lastChartLink");
            i = $("#lastChartLink a");
            i != null && (t = Utilities.readCookie("LastChart"), t == null && (t = "/stock/164/خودرو"), i.attr("href", t));
            n != null && window.location.pathname.indexOf("/stock") == 0 && n.addClass("active");
            n = $("#lastIndexLink");
            i = $("#lastIndexLink a");
            i != null && (t = Utilities.readCookie("LastIndex"), t == null && (t = "/index/1/شاخص_کل"), i.attr("href", t));
            n != null && window.location.pathname.indexOf("/index") == 0 && n.addClass("active");
            n = $("#lastFundLink");
            i = $("#lastFundLink a");
            i != null && (t = Utilities.readCookie("LastFund"), t == null && (t = "/fund/14/"), i.attr("href", t));
            n != null && window.location.pathname.indexOf("/fund") == 0 && n.addClass("active")
        }
    }, n.userId = "", n.searchWithCtrl = !1, n.selectedAssetId = 0, n.selectedAssetType = "", n.feedItemTemplate = "<li  id='{Id}'><time class='timeline-datetime'><span class='timeline-time'>{RegisterTime}<\/span><span class='timeline-date'>{RegisterDay}<\/span><\/time><div class='timeline-badge bg-{BadgeColor} white'><div class='icon {BadgeIcon}'><\/div><i class='{BadgeIcon}'><\/i><\/div><div class='timeline-panel'><div class='timeline-header'><h4 class='timeline-title'>{Title}<\/h4><p class='timeline-datetime'><span class='text-muted'><i class='glyphicon glyphicon-time'><\/i><span class='timeline-date'>{RegisterDay}<\/span>-<span class='timeline-time'>{RegisterTime}<\/span><\/span><\/p><\/div><div class='timeline-body'><p class='body-text {Ellipsis}'>{Body}<\/p><div class='more bordered-{BadgeColor} {More}'><a onclick='expand(this);'>ادامه<\/a><\/div><\/div><\/div><\/li>", n.postItemTemplate = "<li id='{Id}'><time class='posts-datetime'><div class='post-username'>{Username}<\/div><\/time><div class='posts-badge'><img class='post-avatar' src='/home/getavatar?userid={UserId}' /><\/div><div class='posts-panel' style='margin-bottom:20px;'><div class='posts-header'><div class='posts-datetime'><div class='post-username'>{Username}<\/div><\/div><\/div><div class='posts-body'><p class='body-text'><a href='/{AssetTypeShort}/{AssetId}/{AssetShortName}'>{AssetShortName}<\/a><span>&nbsp;&mdash;&nbsp;<\/span> {Body}<\/p><a href='/analysis/{AnalysisId}' target='blank'><img class='post-analysis-image' src='/analysis/{AnalysisId}/thumb' /><\/a><\/div><div class='posts-footer'><time class='post-datetime'><span class='post-time' title='{RegisterDateTime}'>{RegisterDateTime}<\/span><\/time><div class='reply-container hidden'><div class='alert alert-danger hidden reply-state'><\/div><div class='form-group'><textarea class='reply-body form-control' rows='5' placeholder='پاسخ شما به این نظر چیست؟'><\/textarea><\/div><input type='hidden' class='reply-parent-id' value='{Id}' /><div style='text-align: right;'><a class='reply-register btn btn-primary'>ثبت پاسخ<\/a><a class='reply-cancel btn btn-default'>انصراف<\/a><\/div><\/div><a class='reply-link btn btn-sm btn-primary'>پاسخ<\/a><\/div><\/div><ul class='post-replies'><\/ul><\/li>", n.replyItemTemplate = "<li id='{Id}'><time class='reply-datetime'><div class='reply-username'>{Username}<\/div><\/time><div class='reply-badge'><img class='reply-avatar' src='/home/getavatar?userid={UserId}' /><\/div><div class='reply-panel'><div class='reply-body'><p class='body-text'>{Body}<\/p><a href='/analysis/{AnalysisId}' target='blank'><img class='reply-analysis-image' src='/analysis/{AnalysisId}/thumb' /><\/a><\/div><div class='reply-footer'><time class='reply-datetime'><span class='reply-time' title='{RegisterDateTime}'>{RegisterDateTime}<\/span><\/time><\/div><\/div><\/li>", n
}();
AssetOptions = function () {
    function n() {
    }

    return n
}(), function (n) {
    n[n.Stock = 1] = "Stock";
    n[n.Index = 2] = "Index";
    n[n.Fund = 3] = "Fund"
}(AssetType || (AssetType = {}));
Crud = function () {
    function n() {
    }

    return n.Post = function (n, t, i, r) {
        return $.ajax({
            type: "POST",
            url: t,
            data: n,
            contentType: "application/json; charset=utf-8",
            success: i,
            error: r
        }), 0
    }, n.Put = function (n, t, i, r) {
        return $.ajax({
            type: "PUT",
            url: t,
            data: n,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: i,
            error: r
        }), 0
    }, n.Delete = function (n, t, i, r) {
        return $.ajax({
            type: "DELETE",
            url: t,
            data: n,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: i,
            error: r
        }), 0
    }, n.SelectAll = function (n, t, i, r) {
        $.ajax({
            url: t,
            type: "GET",
            contentType: "application/json; charset=utf-8",
            data: n,
            dataType: "json",
            success: i,
            error: r
        })
    }, n
}();
Chart = function () {
    function n() {
    }

    return n.isEmptyChart = function (n) {
        return n.charts[0].panes.length == 1 && n.charts[0].panes[0].sources[0].type == "MainSeries" && n.charts[0].panes[0].sources.length <= 2 && (n.charts[0].panes[0].sources[1] != null && n.charts[0].panes[0].sources[1].type == "Study" && n.charts[0].panes[0].sources[1].metaInfo.description == "Volume" || n.charts[0].panes[0].sources.length <= 1) ? !0 : !1
    }, n.hasEmptyStudyorDrawing = function (n) {
        var t = !1;
        return $.each(n.charts[0].panes[0].sources, function () {
            if (Utilities.isEmpty(this))return t = !0, !1
        }), t
    }, n.validateChartJson = function (n) {
        var t = !1;
        return $.each(n.charts[0].panes[0].sources, function () {
            if (this.type == "MainSeries" && this.state.symbol != "")return t = !0, !1
        }), t
    }, n.setRahavardValues = function (n) {
        return this.adjustmentType != "" && (n.Rahavard365 = {AdjustmentType: this.adjustmentType, PriceType: "2"}), n
    }, n.getRahavardValues = function (n) {
        n.Rahavard365 != null && (this.adjustmentType = n.Rahavard365.AdjustmentType, this.priceType = n.Rahavard365.PriceType)
    }, n.getAdjustmentData = function () {
        var n = this;
        Crud.SelectAll(null, "/stock/" + this.assetId + "/adjustments", function (t) {
            n.adjustmentData = t
        }, null)
    }, n.createChart = function (n, t) {
        this.widget = new TradingView.widget({
            fullscreen: !t,
            symbol: n,
            timezone: "Asia/Tehran",
            toolbar_bg: "#fff",
            allow_symbol_change: !0,
            container_id: "tv_chart_container",
            datafeed: new Datafeeds.UDFCompatibleDatafeed("/"),
            library_path: "/ChartingLibrary/",
            locale: "fa_IR",
            drawings_access: {type: "black", tools: [{name: "Regression Trend"}]},
            disabled_features: t ? ["use_localstorage_for_settings", "left_toolbar", "header_widget", "timeframes_toolbar", "narrow_chart_enabled", "pane_context_menu"] : ["use_localstorage_for_settings"],
            overrides: AssetOptions.groupPrefix == "Fund.Fund" ? {"mainSeriesProperties.style": 2} : undefined
        })
    }, n.loadSavedAnalysisOrDefaultSetting = function () {
        if (this.savedAnalysis != "") {
            console.log("Load Analysis Begins");
            var n = $.parseJSON(this.savedAnalysis);
            this.getRahavardValues(n);
            this.widget.load(n);
            console.log("Load Analysis End")
        } else this.defaultSettingId != "" && (console.log("Load Setting Begins"), $.when(this.getDefaultSetting(this.assetId)).done(function () {
        }))
    }, n.onGetDefaultSettingSuccess = function (n) {
        if (n != null) {
            this.defaultSettingId == "" && (this.defaultSettingId = n.Id);
            var t = $.parseJSON(n.Settings);
            this.getRahavardValues(t);
            this.widget.load(t);
            this.isLoadedCompletely = !0;
            console.log("Load Setting Done.")
        } else this.isLoadedCompletely = !1, console.log("Error in Loading Default Setting;")
    }, n.getDefaultSetting = function (n) {
        var t = this;
        Crud.SelectAll(null, "/Asset/DefaultChartSetting?accountId=" + Global.userId + "&entityType=" + AssetOptions.groupPrefix + "&entityId=" + n, function (n) {
            return t.onGetDefaultSettingSuccess(n)
        }, null)
    }, n.createAdjustmentDropDown = function () {
        var t, i;
        if (AssetOptions.type == 1 && n.adjustmentData != null) {
            t = "<option value=''>بدون تعدیل<\/option>";
            $.each(n.adjustmentData, function () {
                (this.PriceType == "2" || this.PriceType == null) && (t += "<option value='" + this.Type + "'>" + this.Title + "<\/option>")
            });
            n.widget.createButton().attr("class", "sleep").append($("<div style='direction:rtl; width:250px;height: 28px;overflow: hidden; background: url(/ChartingLibrary/static/images/caret.png) no-repeat 4% #fff; border: 1px solid #ccc;'><select id='justificationSelect' style='width:275px; text-align:right;  padding:0 5px; font-family:WYekan; font-size:13px; background: transparent; line-height: 1;border: 0;border-radius: 0;height: 28px;-webkit-appearance: none;'>" + t + "<\/select><\/div>"));
            i = $("#tv_chart_container iframe");
            $("#justificationSelect", i.contents()).val(this.adjustmentType);
            $("#justificationSelect", i.contents()).on("change", function () {
                var t = this;
                n.adjustmentType = this.value != "" ? this.value : "";
                n.widget.setSymbol(this.symbolName, "1D", function () {
                    n.widget.setSymbol(t.symbolName, "D")
                })
            })
        }
    }, n.saveAnalysis = function (n, t, i) {
        var r = this;
        this.widget.save(function (u) {
            var f = u[0], e = u[1];
            r.validateChartJson(f) && !r.hasEmptyStudyorDrawing(f) && (r.isEmptyChart(f) && i(), r.setRahavardValues(f), Crud.Post("{Settings:'" + JSON.stringify(f) + "',EntityId:'" + r.assetId + "',EntityType:'" + AssetOptions.groupPrefix + "',Title:'',Snapshot:'" + JSON.stringify(e) + "'}", "/Asset/ChartAnalysis", n, t))
        })
    }, n.saveDefaultSetting = function (n, t) {
        var i = this;
        this.widget.save(function (r) {
            var u = r[0], f = r[1];
            i.validateChartJson(u) && !i.hasEmptyStudyorDrawing(u) && (i.setRahavardValues(u), Crud.Post("{Settings:'" + JSON.stringify(u) + "',EntityId:'" + i.assetId + "',EntityType:'" + AssetOptions.groupPrefix + "',Id:'" + i.defaultSettingId + "'}", "/Asset/DefaultChartSetting", n, t))
        })
    }, n.setLastChartCookie = function () {
        Utilities.createCookie("LastChart", "/stock/" + this.assetId + "/" + this.symbolName.split(" ").join("_"), 30)
    }, n.setLastIndexCookie = function () {
        Utilities.createCookie("LastIndex", "/index/" + this.assetId + "/" + this.symbolName.split(" ").join("_"), 30)
    }, n.setLastFundCookie = function () {
        Utilities.createCookie("LastFund", "/fund/" + this.assetId + "/" + this.symbolName.split(" ").join("_"), 30)
    }, n.autoSaveChart = function () {
        var n = this;
        Global.userId == "" ? $("#loginModal").modal({
            backdrop: "static",
            keyboard: !1
        }) : (Utilities.readCookie("returning-user") || (Utilities.createCookie("returning-user", "true", 100), Asset.startIntro()), this.savedAnalysis == "" && (this.isLoadedCompletely || this.defaultSettingId == "") && (console.log("Save Settings Begins"), this.saveDefaultSetting(function (t) {
            t.State != 2 ? (n.defaultSettingId = t.Data.Id, console.log("Save Settings Ends")) : console.log("Error in Saving Default Setting: " + t.Error.Code)
        }, null)))
    }, n.registerPost = function (t) {
        Crud.Post(JSON.stringify(t), "/asset/registerpost/" + n.assetId, function (n) {
            $(".loading").hide();
            n == "Ok" ? ($("#PostModal").modal("hide"), mixpanel.track("New Post", {
                "Share Analysis": !0,
                "Account Id": Global.userId || "0"
            })) : ($("#postState").html(n), $("#postState").removeClass("hidden"))
        }, null)
    }, n.registerReply = function (n) {
        Crud.Post(JSON.stringify(n), "/asset/registerpostreply/" + $("#parentId").val(), function (n) {
            $(".loading").hide();
            n == "Ok" ? ($("#replyModal").modal("hide"), mixpanel.track("New Post Reply", {
                "Share Analysis": !0,
                "Account Id": Global.userId || "0"
            })) : ($("#replyState").html(n), $("#replyState").removeClass("hidden"))
        }, null)
    }, n.adjustmentData = null, n.assetId = "", n.ticker = "", n.assetName = "", n.symbolName = "", n.widget = null, n.savedAnalysis = "", n.defaultSettingId = "", n.priceType = "", n.adjustmentType = "", n.isLoadedCompletely = !1, n.isAnalysis = !1, n.isSignalrInitialized = !1, n.EmptyChartMessage = "نمودار شما هیچ اندیکاتور، تصویر و یا متنی ندارد.‎", n
}()