import gitbucket.core.plugin.{RenderRequest, Renderer}
import play.twirl.api.Html
import gitbucket.core.view.helpers.urlEncode

class CsvRenderer extends Renderer {
  def render(req: RenderRequest): Html = {
    val url = s"${req.context.path}/${req.repository.owner}/${req.repository.name}/raw/${req.branch}/" +
      req.filePath.mkString("/")
    Html(
      s"""
         |<link href="${req.context.path}/plugin-assets/csv/css/tabulator.css" rel="stylesheet">
         |<script src="${req.context.path}/plugin-assets/csv/js/papaparse.min.js"></script>
         |<script src="${req.context.path}/plugin-assets/csv/js/tabulator.min.js"></script>
         |<div id="csv-table"></div>
         |<script>
         |var csv = Papa.parse("${url}", {
         |  download: true,
         |  header: true,
         |  dynamicTyping: true,
         |  complete: function(results) {
         |    var columns = results.meta.fields.map(function(c){return {title: c, field: c}})
         |    var table = new Tabulator("#csv-table", {
         |      data: results.data,
         |      layout: "fitColumns",
         |      columns: columns
         |    })
         |  }
         |})
         |</script>""".stripMargin)
  }
}