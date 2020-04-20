package couclou.fr.nixconfighelper

class Feed(val page: Int,
           val count: Int,
           val totalPages: Int,
           val totalCount: Int,
           val elements:List<Element>)