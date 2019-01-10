import io.github.gitbucket.solidbase.model.Version
import gitbucket.core.plugin.PluginRegistry
import gitbucket.core.service.SystemSettingsService
import javax.servlet.ServletContext

class Plugin extends gitbucket.core.plugin.Plugin {
  override val pluginId: String = "csv"
  override val pluginName: String = "CSV renderer Plugin"
  override val description: String = "csv renderer plug-in"
  override val versions: List[Version] = List(new Version("1.0.0"))

  override val assetsMappings: Seq[(String, String)] = Seq("/csv" -> "/assets")

  override def initialize(registry: PluginRegistry, context: ServletContext, settings: SystemSettingsService.SystemSettings): Unit = {
    super.initialize(registry, context, settings)
    val renderer = new CsvRenderer()
    registry.addRenderer("csv", renderer)
  }
}
