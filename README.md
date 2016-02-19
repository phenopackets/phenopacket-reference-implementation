Initial pass at phenopacket java library.

Approach is to access at YAML level (via snakeyaml). Other options are
at the semantically equivalent level (e.g. via GSON) or jump straight
to JSON-LD (use Jena).
